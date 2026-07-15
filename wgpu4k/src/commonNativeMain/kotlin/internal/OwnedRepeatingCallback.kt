@file:OptIn(kotlin.concurrent.atomics.ExperimentalAtomicApi::class)

package io.ygdrasil.webgpu.internal

import kotlin.concurrent.atomics.AtomicBoolean
import kotlin.concurrent.atomics.AtomicReference

internal class OwnedRepeatingCallback<T>(
    private val callback: (T) -> Unit,
) : AutoCloseable {
    private val closed = AtomicBoolean(false)
    private val registration = AtomicReference<AutoCloseable?>(null)

    internal fun attach(value: AutoCloseable): Boolean {
        check(registration.compareAndSet(expectedValue = null, newValue = value)) {
            "Repeating callback already has a registration"
        }
        if (closed.load()) {
            registration.exchange(null)?.close()
            return false
        }
        return true
    }

    internal fun dispatch(value: T): Boolean {
        if (closed.load()) return false
        callback(value)
        return true
    }

    override fun close() {
        if (closed.compareAndSet(expectedValue = false, newValue = true)) {
            registration.exchange(null)?.close()
        }
    }
}
