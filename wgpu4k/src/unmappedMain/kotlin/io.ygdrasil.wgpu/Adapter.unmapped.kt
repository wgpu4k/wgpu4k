package io.ygdrasil.wgpu

actual class Adapter : AutoCloseable {
    actual suspend fun requestDevice(): Device? {
        TODO("Not yet implemented")
    }

    actual override fun close() {
    }

}