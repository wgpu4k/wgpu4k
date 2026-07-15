package io.ygdrasil.webgpu.internal

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.test.runTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference
import kotlin.concurrent.thread

class NativeCallbackOperationTest : FreeSpec({
    "only the first result is delivered and cleanup runs once" {
        runTest {
            var registrationCloses = 0
            var attachmentCloses = 0
            var finishes = 0
            lateinit var operation: NativeCallbackOperation<Int>
            val result = async(start = CoroutineStart.UNDISPATCHED) {
                suspendCancellableCoroutine<Result<Int>> { continuation ->
                    operation = NativeCallbackOperation(
                        continuation = continuation,
                        closeRegistration = { registrationCloses++ },
                        onFinished = { finishes++ },
                    )
                    operation.attach(AutoCloseable { attachmentCloses++ })
                }
            }

            operation.succeed(7) shouldBe true
            operation.succeed(8) shouldBe false
            result.await().getOrThrow() shouldBe 7
            registrationCloses shouldBe 1
            attachmentCloses shouldBe 1
            finishes shouldBe 1
        }
    }

    "cancellation closes callback state and rejects a late value" {
        runTest {
            var registrationCloses = 0
            var undelivered: Int? = null
            lateinit var operation: NativeCallbackOperation<Int>
            val result = async(start = CoroutineStart.UNDISPATCHED) {
                suspendCancellableCoroutine<Result<Int>> { continuation ->
                    operation = NativeCallbackOperation(
                        continuation = continuation,
                        closeRegistration = { registrationCloses++ },
                    )
                }
            }

            result.cancelAndJoin()
            operation.succeed(9) { undelivered = it } shouldBe false
            registrationCloses shouldBe 1
            undelivered shouldBe 9
        }
    }

    "an attachment added after cancellation is closed immediately" {
        runTest {
            var attachmentCloses = 0
            lateinit var operation: NativeCallbackOperation<Unit>
            val result = async(start = CoroutineStart.UNDISPATCHED) {
                suspendCancellableCoroutine<Result<Unit>> { continuation ->
                    operation = NativeCallbackOperation(
                        continuation = continuation,
                        closeRegistration = {},
                    )
                }
            }

            result.cancelAndJoin()
            operation.attach(AutoCloseable { attachmentCloses++ }) shouldBe false
            attachmentCloses shouldBe 1
        }
    }

    "failure survives cleanup exceptions and is delivered once" {
        runTest {
            var registrationCloses = 0
            var attachmentCloses = 0
            var finishes = 0
            lateinit var operation: NativeCallbackOperation<Int>
            val result = async(
                context = CoroutineExceptionHandler { _, _ -> },
                start = CoroutineStart.UNDISPATCHED,
            ) {
                suspendCancellableCoroutine<Result<Int>> { continuation ->
                    operation = NativeCallbackOperation(
                        continuation = continuation,
                        closeRegistration = {
                            registrationCloses++
                            error("registration close failed")
                        },
                        onFinished = {
                            finishes++
                            error("finish hook failed")
                        },
                    )
                    operation.attach(
                        AutoCloseable {
                            attachmentCloses++
                            error("attachment close failed")
                        }
                    )
                }
            }
            val failure = IllegalStateException("native callback failed")

            val completion = runCatching { operation.fail(failure) }
            if (completion.isFailure) result.cancelAndJoin()

            completion.getOrThrow() shouldBe true
            result.await().exceptionOrNull() shouldBe failure
            operation.fail(AssertionError("late failure")) shouldBe false
            registrationCloses shouldBe 1
            attachmentCloses shouldBe 1
            finishes shouldBe 1
        }
    }

    "CancellationException cancels the continuation instead of becoming a Result failure" {
        runTest {
            var registrationCloses = 0
            var finishes = 0
            lateinit var operation: NativeCallbackOperation<Int>
            val result = async(start = CoroutineStart.UNDISPATCHED) {
                suspendCancellableCoroutine<Result<Int>> { continuation ->
                    operation = NativeCallbackOperation(
                        continuation = continuation,
                        closeRegistration = { registrationCloses++ },
                        onFinished = { finishes++ },
                    )
                }
            }
            val cancellation = CancellationException("native callback cancelled")

            operation.fail(cancellation) shouldBe true
            val awaitOutcome: Result<Result<Int>> = runCatching { result.await() }

            awaitOutcome.exceptionOrNull() shouldBe cancellation
            operation.isFinished shouldBe true
            registrationCloses shouldBe 1
            finishes shouldBe 1
        }
    }

    "late disposer and attachment close exceptions are contained" {
        runTest {
            var undelivered = 0
            var attachmentCloses = 0
            lateinit var operation: NativeCallbackOperation<Int>
            val result = async(
                context = CoroutineExceptionHandler { _, _ -> },
                start = CoroutineStart.UNDISPATCHED,
            ) {
                suspendCancellableCoroutine<Result<Int>> { continuation ->
                    operation = NativeCallbackOperation(
                        continuation = continuation,
                        closeRegistration = {},
                    )
                }
            }

            operation.succeed(7) shouldBe true
            result.await().getOrThrow() shouldBe 7

            runCatching {
                operation.succeed(8) {
                    undelivered++
                    error("late disposer failed")
                }
            }.getOrThrow() shouldBe false
            runCatching {
                operation.attach(
                    AutoCloseable {
                        attachmentCloses++
                        error("late attachment close failed")
                    }
                )
            }.getOrThrow() shouldBe false
            undelivered shouldBe 1
            attachmentCloses shouldBe 1
        }
    }

    "cleanup and disposer failures are reported once without blocking completion" {
        runTest {
            val attachmentFailure = IllegalStateException("attachment close failed")
            val registrationFailure = IllegalStateException("registration close failed")
            val finishFailure = IllegalStateException("finish hook failed")
            val disposerFailure = IllegalStateException("late disposer failed")
            val reported = mutableListOf<Throwable>()
            val exceptionHandler = CoroutineExceptionHandler { _, error -> reported += error }
            lateinit var operation: NativeCallbackOperation<Int>
            val result = async(
                context = exceptionHandler,
                start = CoroutineStart.UNDISPATCHED,
            ) {
                suspendCancellableCoroutine<Result<Int>> { continuation ->
                    operation = NativeCallbackOperation(
                        continuation = continuation,
                        closeRegistration = { throw registrationFailure },
                        onFinished = { throw finishFailure },
                    )
                    operation.attach(AutoCloseable { throw attachmentFailure })
                }
            }

            val completion = runCatching { operation.succeed(7) }
            if (completion.isFailure) result.cancelAndJoin()

            completion.getOrThrow() shouldBe true
            result.await().getOrThrow() shouldBe 7
            runCatching {
                operation.succeed(8) { throw disposerFailure }
            }.getOrThrow() shouldBe false
            reported shouldBe listOf(
                attachmentFailure,
                registrationFailure,
                finishFailure,
                disposerFailure,
            )
        }
    }

    "a throwing reporter is contained and invoked once" {
        runTest {
            val cleanupFailure = IllegalStateException("registration close failed")
            val reporterFailure = IllegalStateException("reporter failed")
            var cleanupCalls = 0
            var reporterCalls = 0
            var reported: Throwable? = null
            lateinit var operation: NativeCallbackOperation<Int>
            val result = async(start = CoroutineStart.UNDISPATCHED) {
                suspendCancellableCoroutine<Result<Int>> { continuation ->
                    operation = NativeCallbackOperation(
                        continuation = continuation,
                        closeRegistration = {
                            cleanupCalls++
                            throw cleanupFailure
                        },
                        reportException = {
                            reporterCalls++
                            reported = it
                            throw reporterFailure
                        },
                    )
                }
            }

            val completion = runCatching { operation.succeed(7) }
            if (completion.isFailure) result.cancelAndJoin()

            completion.getOrThrow() shouldBe true
            result.await().getOrThrow() shouldBe 7
            cleanupCalls shouldBe 1
            reporterCalls shouldBe 1
            reported shouldBe cleanupFailure
        }
    }

    "callback cancellation and attach race still terminates exactly once" {
        runTest {
            val registrationEntered = CountDownLatch(1)
            val releaseRegistration = CountDownLatch(1)
            val contendersStart = CountDownLatch(1)
            val contendersDone = CountDownLatch(2)
            val registrationCloses = AtomicInteger(0)
            val attachmentCloses = AtomicInteger(0)
            val finishes = AtomicInteger(0)
            val undelivered = AtomicInteger(0)
            val callbackOutcome = AtomicReference<Result<Boolean>?>(null)
            val attachOutcome = AtomicReference<Result<Boolean>?>(null)
            lateinit var operation: NativeCallbackOperation<Int>
            val result = async(start = CoroutineStart.UNDISPATCHED) {
                suspendCancellableCoroutine<Result<Int>> { continuation ->
                    operation = NativeCallbackOperation(
                        continuation = continuation,
                        closeRegistration = {
                            registrationCloses.incrementAndGet()
                            registrationEntered.countDown()
                            check(releaseRegistration.await(5, TimeUnit.SECONDS)) {
                                "registration cleanup was not released"
                            }
                        },
                        onFinished = { finishes.incrementAndGet() },
                    )
                }
            }
            val cancellation = CancellationException("controlled cancellation")

            val callbackThread = thread(name = "native-callback") {
                callbackOutcome.set(
                    runCatching {
                        operation.succeed(11) { undelivered.incrementAndGet() }
                    }
                )
            }
            registrationEntered.await(5, TimeUnit.SECONDS) shouldBe true

            val attachThread = thread(name = "callback-attach") {
                check(contendersStart.await(5, TimeUnit.SECONDS))
                try {
                    attachOutcome.set(
                        runCatching {
                            operation.attach(
                                AutoCloseable { attachmentCloses.incrementAndGet() }
                            )
                        }
                    )
                } finally {
                    contendersDone.countDown()
                }
            }
            val cancellationThread = thread(name = "callback-cancellation") {
                check(contendersStart.await(5, TimeUnit.SECONDS))
                try {
                    result.cancel(cancellation)
                } finally {
                    contendersDone.countDown()
                }
            }

            contendersStart.countDown()
            contendersDone.await(5, TimeUnit.SECONDS) shouldBe true
            releaseRegistration.countDown()
            callbackThread.join(5_000)
            attachThread.join(5_000)
            cancellationThread.join(5_000)

            callbackThread.isAlive shouldBe false
            attachThread.isAlive shouldBe false
            cancellationThread.isAlive shouldBe false
            callbackOutcome.get()?.getOrThrow() shouldBe false
            attachOutcome.get()?.getOrThrow() shouldBe false
            result.join()
            result.isCancelled shouldBe true
            operation.isFinished shouldBe true
            registrationCloses.get() shouldBe 1
            attachmentCloses.get() shouldBe 1
            finishes.get() shouldBe 1
            undelivered.get() shouldBe 1
        }
    }
})
