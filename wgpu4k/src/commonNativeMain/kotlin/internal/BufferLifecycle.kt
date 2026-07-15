@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.webgpu.internal

import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.AtomicReference

internal class BufferLifecycle(
    private val release: () -> Unit,
) : AutoCloseable {
    internal class PendingMap(
        val onTerminated: (Throwable) -> Unit,
    )

    private data class State(
        val closed: Boolean = false,
        val pendingMap: PendingMap? = null,
        val activeNativeCalls: Int = 0,
        val released: Boolean = false,
    )

    private val state = AtomicReference(State())

    internal fun registerMap(onTerminated: (Throwable) -> Unit): Result<Ticket> {
        val pending = PendingMap(onTerminated)
        while (true) {
            val current = state.load()
            if (current.closed) {
                return Result.failure(IllegalStateException("Buffer is closed"))
            }
            if (current.pendingMap != null) {
                return Result.failure(IllegalStateException("Buffer map is already pending"))
            }
            if (state.compareAndSet(current, current.copy(pendingMap = pending))) {
                return Result.success(Ticket(this, pending))
            }
        }
    }

    override fun close() {
        var termination: ((Throwable) -> Unit)? = null
        while (true) {
            val current = state.load()
            if (current.closed) return
            termination = current.pendingMap?.onTerminated
            if (state.compareAndSet(
                    current,
                    current.copy(closed = true, pendingMap = null),
                )
            ) {
                break
            }
        }
        try {
            termination?.invoke(IllegalStateException("Buffer is closed"))
        } finally {
            releaseIfQuiescent()
        }
    }

    private fun remove(pending: PendingMap) {
        while (true) {
            val current = state.load()
            if (current.pendingMap !== pending) return
            if (state.compareAndSet(current, current.copy(pendingMap = null))) return
        }
    }

    private fun beginNativeCall(pending: PendingMap): Boolean {
        while (true) {
            val current = state.load()
            if (current.closed || current.released || current.pendingMap !== pending) return false
            if (state.compareAndSet(
                    current,
                    current.copy(activeNativeCalls = current.activeNativeCalls + 1),
                )
            ) {
                return true
            }
        }
    }

    private fun endNativeCall() {
        while (true) {
            val current = state.load()
            check(current.activeNativeCalls > 0)
            if (state.compareAndSet(
                    current,
                    current.copy(activeNativeCalls = current.activeNativeCalls - 1),
                )
            ) {
                releaseIfQuiescent()
                return
            }
        }
    }

    private fun releaseIfQuiescent() {
        while (true) {
            val current = state.load()
            if (!current.closed || current.activeNativeCalls != 0 || current.released) return
            if (state.compareAndSet(current, current.copy(released = true))) {
                release()
                return
            }
        }
    }

    internal class Ticket internal constructor(
        private val owner: BufferLifecycle,
        private val pending: PendingMap,
    ) : AutoCloseable {
        private val closed = AtomicBoolean(false)

        internal fun runNativeCall(block: () -> Unit): Boolean {
            if (!owner.beginNativeCall(pending)) return false
            return try {
                block()
                true
            } finally {
                owner.endNativeCall()
            }
        }

        override fun close() {
            if (closed.compareAndSet(expectedValue = false, newValue = true)) {
                owner.remove(pending)
            }
        }
    }
}
