package io.ygdrasil.webgpu.examples.headless

internal class OwnedRegistrationSlot<T : AutoCloseable> {
    private val lock = Any()
    private var owned: T? = null

    fun replace(install: () -> T) {
        synchronized(lock) {
            val replacement = install()
            val previous = owned
            owned = replacement
            previous?.close()
        }
    }
}
