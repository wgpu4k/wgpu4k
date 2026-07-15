@file:OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)

package io.ygdrasil.webgpu.internal

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.async
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

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
