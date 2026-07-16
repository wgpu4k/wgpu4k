@file:OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)

package io.ygdrasil.webgpu.internal

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference

class DeviceEventPumpTest : FreeSpec({
    "idle pump does not poll and concurrent tickets share one loop" {
        runTest {
            var polls = 0
            var retains = 0
            var releases = 0
            val pump = DeviceEventPump(
                dispatcher = StandardTestDispatcher(testScheduler),
                poll = { polls++ },
                retain = { retains++ },
                release = { releases++ },
            )
            runCurrent()
            polls shouldBe 0
            retains shouldBe 1

            val firstTicket = pump.tryRegister(needsPolling = true) {}
            val secondTicket = pump.tryRegister(needsPolling = true) {}
            runCurrent()
            polls shouldBe 1
            firstTicket!!.close()
            advanceTimeBy(1)
            runCurrent()
            polls shouldBe 2
            secondTicket!!.close()
            advanceTimeBy(1)
            runCurrent()
            polls shouldBe 2

            pump.close()
            runCurrent()
            releases shouldBe 1
        }
    }

    "one device close failure is delivered to every pending operation" {
        runTest {
            val failures = mutableListOf<String>()
            val pump = DeviceEventPump(
                dispatcher = StandardTestDispatcher(testScheduler),
                poll = {}, retain = {}, release = {},
            )
            pump.tryRegister(true) { failures += it.message.orEmpty() }
            pump.tryRegister(false) { failures += it.message.orEmpty() }

            pump.close()
            failures shouldBe listOf("Device is closed", "Device is closed")
            pump.tryRegister(true) {} shouldBe null
        }
    }

    "a throwing termination callback does not block remaining callbacks or release" {
        runTest {
            var delivered: Throwable? = null
            var releases = 0
            var reported: Throwable? = null
            val pump = DeviceEventPump(
                dispatcher = StandardTestDispatcher(testScheduler),
                poll = {},
                retain = {},
                release = { releases++ },
                reportException = { reported = it },
            )
            pump.tryRegister(false) { error("callback failed") }
            pump.tryRegister(false) { delivered = it }

            pump.close()
            runCurrent()

            delivered?.message shouldBe "Device is closed"
            releases shouldBe 1
            reported?.message shouldBe "callback failed"
        }
    }

    "a release failure on close is reported once and cannot escape" {
        runTest {
            val releases = AtomicInteger(0)
            val reports = AtomicInteger(0)
            val reported = AtomicReference<Throwable?>(null)
            val pump = DeviceEventPump(
                dispatcher = StandardTestDispatcher(testScheduler),
                poll = {},
                retain = {},
                release = {
                    releases.incrementAndGet()
                    error("release failed")
                },
                reportException = {
                    reports.incrementAndGet()
                    reported.set(it)
                    error("reporter failed")
                },
            )

            pump.close()
            runCurrent()
            pump.close()
            runCurrent()

            releases.get() shouldBe 1
            reports.get() shouldBe 1
            reported.get()?.message shouldBe "release failed"
        }
    }

    "a release failure after the last downcall is reported once and cannot escape" {
        runTest {
            val entered = CountDownLatch(1)
            val leave = CountDownLatch(1)
            val releases = AtomicInteger(0)
            val reports = AtomicInteger(0)
            val reported = AtomicReference<Throwable?>(null)
            val executor = Executors.newSingleThreadExecutor()
            val pump = DeviceEventPump(
                dispatcher = StandardTestDispatcher(testScheduler),
                poll = {},
                retain = {},
                release = {
                    releases.incrementAndGet()
                    error("release failed after downcall")
                },
                reportException = {
                    reports.incrementAndGet()
                    reported.set(it)
                },
            )
            val ticket = pump.tryRegister(false) {}!!
            val future = executor.submit<Boolean> {
                ticket.runNativeCall {
                    entered.countDown()
                    leave.await(5, TimeUnit.SECONDS)
                }
            }
            entered.await(5, TimeUnit.SECONDS) shouldBe true

            pump.close()
            runCurrent()
            releases.get() shouldBe 0
            leave.countDown()

            future.get(5, TimeUnit.SECONDS) shouldBe true
            executor.shutdown()
            executor.awaitTermination(5, TimeUnit.SECONDS) shouldBe true
            releases.get() shouldBe 1
            reports.get() shouldBe 1
            reported.get()?.message shouldBe "release failed after downcall"
        }
    }

    "a poll failure terminates pending operations instead of hanging" {
        runTest {
            var failure: Throwable? = null
            val pump = DeviceEventPump(
                dispatcher = StandardTestDispatcher(testScheduler),
                poll = { error("device lost") },
                retain = {},
                release = {},
            )
            pump.tryRegister(needsPolling = true) { failure = it }

            runCurrent()
            failure?.message shouldBe "Device event pump failed"
            failure?.cause?.message shouldBe "device lost"
            pump.tryRegister(needsPolling = true) {} shouldBe null
        }
    }

    "resource tasks map synchronous success and failure to Result" {
        runTest {
            val pump = DeviceEventPump(
                dispatcher = StandardTestDispatcher(testScheduler),
                poll = {}, retain = {}, release = {},
            )
            val success = async {
                pump.runResourceTask(create = { "pipeline" }, dispose = {})
            }
            runCurrent()
            success.await().getOrThrow() shouldBe "pipeline"

            val failure = async {
                pump.runResourceTask<String>(
                    create = { error("creation failed") },
                    dispose = {},
                )
            }
            runCurrent()
            failure.await().exceptionOrNull()?.message shouldBe "creation failed"

            pump.close()
            runCurrent()
        }
    }

    "resource task cancellation propagates and disposes a late value exactly once" {
        runTest {
            val entered = CountDownLatch(1)
            val leave = CountDownLatch(1)
            val disposed = CountDownLatch(1)
            val disposals = AtomicInteger(0)
            val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
            val pump = DeviceEventPump(
                dispatcher = dispatcher,
                poll = {},
                retain = {},
                release = {},
            )
            try {
                val task = async {
                    pump.runResourceTask(
                        create = {
                            entered.countDown()
                            leave.await(5, TimeUnit.SECONDS)
                            "pipeline"
                        },
                        dispose = {
                            disposals.incrementAndGet()
                            disposed.countDown()
                        },
                    )
                }
                runCurrent()
                entered.await(5, TimeUnit.SECONDS) shouldBe true

                task.cancel(CancellationException("resource task cancelled"))
                task.join()
                val cancellation = try {
                    task.await()
                    null
                } catch (error: CancellationException) {
                    error
                }
                cancellation?.message shouldBe "resource task cancelled"

                leave.countDown()
                disposed.await(5, TimeUnit.SECONDS) shouldBe true
                disposals.get() shouldBe 1
            } finally {
                leave.countDown()
                pump.close()
                dispatcher.close()
            }
        }
    }

    "a resource created after device close is disposed" {
        val entered = CountDownLatch(1)
        val proceed = CountDownLatch(1)
        val released = CountDownLatch(1)
        val disposed = AtomicInteger(0)
        val releases = AtomicInteger(0)
        val executor = Executors.newSingleThreadExecutor()
        val dispatcher = executor.asCoroutineDispatcher()
        try {
            withTimeout(5_000) {
                coroutineScope {
                    val pump = DeviceEventPump(
                        dispatcher = dispatcher,
                        poll = {},
                        retain = {},
                        release = {
                            releases.incrementAndGet()
                            released.countDown()
                        },
                    )
                    val result = async {
                        pump.runResourceTask(
                            create = {
                                entered.countDown()
                                check(proceed.await(5, TimeUnit.SECONDS))
                                Any()
                            },
                            dispose = { disposed.incrementAndGet() },
                        )
                    }

                    try {
                        withContext(Dispatchers.IO) {
                            entered.await(5, TimeUnit.SECONDS)
                        } shouldBe true
                        pump.close()
                        result.await().exceptionOrNull()?.message shouldBe "Device is closed"
                    } finally {
                        proceed.countDown()
                    }

                    withContext(Dispatchers.IO) {
                        released.await(5, TimeUnit.SECONDS)
                    } shouldBe true
                    disposed.get() shouldBe 1
                    releases.get() shouldBe 1
                }
            }
        } finally {
            dispatcher.close()
            executor.shutdownNow()
            executor.awaitTermination(5, TimeUnit.SECONDS)
        }
    }

    "base native reference outlives an active downcall" {
        val entered = CountDownLatch(1)
        val leave = CountDownLatch(1)
        val released = CountDownLatch(1)
        val releases = AtomicInteger(0)
        val executor = Executors.newSingleThreadExecutor()
        val pump = DeviceEventPump(
            poll = {}, retain = {}, release = {
                releases.incrementAndGet()
                released.countDown()
            },
        )
        val ticket = pump.tryRegister(false) {}!!
        val future = executor.submit<Boolean> {
            ticket.runNativeCall {
                entered.countDown()
                leave.await(5, TimeUnit.SECONDS)
            }
        }
        entered.await(5, TimeUnit.SECONDS) shouldBe true

        pump.close()
        releases.get() shouldBe 0
        leave.countDown()
        future.get(5, TimeUnit.SECONDS) shouldBe true
        executor.shutdown()
        executor.awaitTermination(5, TimeUnit.SECONDS) shouldBe true
        released.await(5, TimeUnit.SECONDS) shouldBe true
        releases.get() shouldBe 1
    }
})
