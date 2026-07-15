@file:OptIn(
    kotlinx.coroutines.InternalCoroutinesApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
)

package io.ygdrasil.webgpu.internal

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CancellationException
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
            attachment.exchange(null)?.let { closeSafely(it) }
            return false
        }
        return true
    }

    internal fun succeed(value: T, onUndelivered: (T) -> Unit = {}): Boolean =
        complete(Result.success(value), onUndelivered)

    internal fun fail(error: Throwable): Boolean =
        if (error is CancellationException) cancel(error) else complete(Result.failure(error))

    private fun cancel(error: CancellationException): Boolean {
        if (!finished.compareAndSet(expectedValue = false, newValue = true)) return false
        cleanup()
        return continuation.cancel(error)
    }

    private fun complete(result: Result<T>, onUndelivered: (T) -> Unit = {}): Boolean {
        if (!finished.compareAndSet(expectedValue = false, newValue = true)) {
            result.getOrNull()?.let { disposeSafely(it, onUndelivered) }
            return false
        }
        cleanup()
        val token = continuation.tryResume(result, idempotent = null) { _, rejected, _ ->
            rejected.getOrNull()?.let { disposeSafely(it, onUndelivered) }
        }
        if (token == null) {
            result.getOrNull()?.let { disposeSafely(it, onUndelivered) }
            return false
        }
        continuation.completeResume(token)
        return true
    }

    private fun cleanup() {
        attachment.exchange(null)?.let { closeSafely(it) }
        runSafely(closeRegistration)
        runSafely(onFinished)
    }

    private fun closeSafely(value: AutoCloseable) {
        runSafely(value::close)
    }

    private fun disposeSafely(value: T, disposer: (T) -> Unit) {
        runSafely { disposer(value) }
    }

    private fun runSafely(block: () -> Unit) {
        try {
            block()
        } catch (_: Throwable) {
            // Cleanup can run from an FFI callback and must never escape its trampoline.
        }
    }
}
