package io.ygdrasil.wgpu

actual class Adapter(internal val handler: Long) : AutoCloseable {

    actual suspend fun requestDevice(descriptor: DeviceDescriptor): Device? {
        TODO("Not yet implemented")
    }

    actual override fun close() {
    }

}