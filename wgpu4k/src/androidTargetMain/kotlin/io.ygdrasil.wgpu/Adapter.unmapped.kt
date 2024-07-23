package io.ygdrasil.wgpu

actual class Adapter : AutoCloseable {

    actual suspend fun requestDevice(descriptor: DeviceDescriptor): Device? {
        TODO("Not yet implemented")
    }

    actual override fun close() {
    }

}