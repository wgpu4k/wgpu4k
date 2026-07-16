@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.webgpu.internal

import kotlin.concurrent.atomics.AtomicReference

internal class OwnedRepeatingCallback<T>(
    private val callback: (T) -> Unit,
) : AutoCloseable {
    private data class State(
        val closed: Boolean = false,
        val activeDispatches: Int = 0,
        val registrationAttached: Boolean = false,
        val registration: AutoCloseable? = null,
        val onQuiescent: (() -> Unit)? = null,
        val cleanupClaimed: Boolean = false,
    )

    private val state = AtomicReference(State())

    internal fun attach(value: AutoCloseable): Boolean {
        while (true) {
            val current = state.load()
            check(!current.registrationAttached) {
                "Repeating callback already has a registration"
            }
            if (current.cleanupClaimed) {
                if (state.compareAndSet(current, current.copy(registrationAttached = true))) {
                    value.close()
                    return false
                }
                continue
            }
            if (state.compareAndSet(
                    current,
                    current.copy(
                        registrationAttached = true,
                        registration = value,
                    ),
                )
            ) {
                drainIfQuiescent()
                return !current.closed
            }
        }
    }

    internal fun dispatch(value: T): Boolean = dispatchWhileActive { deliver -> deliver(value) }

    /**
     * Admits an entire native upcall before running any work that may read native memory.
     *
     * [block] receives the user callback only after the dispatch has incremented the active count.
     * Device teardown therefore waits for native value conversion as well as user delivery.
     */
    internal fun dispatchWhileActive(block: ((T) -> Unit) -> Unit): Boolean {
        while (true) {
            val current = state.load()
            if (current.closed) return false
            if (state.compareAndSet(
                    current,
                    current.copy(activeDispatches = current.activeDispatches + 1),
                )
            ) {
                break
            }
        }
        try {
            block(callback)
        } finally {
            finishDispatch()
        }
        return true
    }

    internal fun closeWhenQuiescent(onQuiescent: () -> Unit) {
        while (true) {
            val current = state.load()
            if (current.closed) return
            if (state.compareAndSet(
                    current,
                    current.copy(
                        closed = true,
                        onQuiescent = onQuiescent,
                    ),
                )
            ) {
                drainIfQuiescent()
                return
            }
        }
    }

    override fun close() = closeWhenQuiescent {}

    private fun finishDispatch() {
        while (true) {
            val current = state.load()
            check(current.activeDispatches > 0)
            if (state.compareAndSet(
                    current,
                    current.copy(activeDispatches = current.activeDispatches - 1),
                )
            ) {
                drainIfQuiescent()
                return
            }
        }
    }

    private fun drainIfQuiescent() {
        while (true) {
            val current = state.load()
            if (!current.closed || current.activeDispatches != 0 || current.cleanupClaimed) return
            if (state.compareAndSet(
                    current,
                    current.copy(
                        registration = null,
                        onQuiescent = null,
                        cleanupClaimed = true,
                    ),
                )
            ) {
                try {
                    current.registration?.close()
                } finally {
                    current.onQuiescent?.invoke()
                }
                return
            }
        }
    }
}
