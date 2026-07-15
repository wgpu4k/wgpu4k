package io.ygdrasil.webgpu.internal

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.test.runTest

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
})
