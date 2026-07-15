@file:OptIn(
    kotlinx.coroutines.InternalCoroutinesApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
)

package io.ygdrasil.webgpu.internal

import kotlinx.coroutines.CancellableContinuation
import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.AtomicReference

internal class NativeCallbackOperation<T>(
    private val continuation: CancellableContinuation<Result<T>>,
    private val closeRegistration: () -> Unit,
    private val onFinished: () -> Unit = {},
) {
    private val finished = AtomicBoolean(false)
    private val attachment = AtomicReference<AutoCloseable?>(null)

    internal val isFinished: Boolean
        get() = finished.load()

    init {
        continuation.invokeOnCancellation {
            if (finished.compareAndSet(expectedValue = false, newValue = true)) cleanup()
        }
    }

    internal fun attach(value: AutoCloseable): Boolean {
        check(attachment.compareAndSet(expectedValue = null, newValue = value)) {
            "Native callback operation already has an attachment"
        }
        if (finished.load()) {
            attachment.exchange(null)?.close()
            return false
        }
        return true
    }

    internal fun succeed(value: T, onUndelivered: (T) -> Unit = {}): Boolean =
        complete(Result.success(value), onUndelivered)

    internal fun fail(error: Throwable): Boolean = complete(Result.failure(error))

    private fun complete(result: Result<T>, onUndelivered: (T) -> Unit = {}): Boolean {
        if (!finished.compareAndSet(expectedValue = false, newValue = true)) {
            result.getOrNull()?.let(onUndelivered)
            return false
        }
        cleanup()
        val token = continuation.tryResume(result, idempotent = null) { _, rejected, _ ->
            rejected.getOrNull()?.let(onUndelivered)
        }
        if (token == null) {
            result.getOrNull()?.let(onUndelivered)
            return false
        }
        continuation.completeResume(token)
        return true
    }

    private fun cleanup() {
        try {
            attachment.exchange(null)?.close()
        } finally {
            try {
                closeRegistration()
            } finally {
                onFinished()
            }
        }
    }
}
