package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class Adapter(internal val handler: Long) : AutoCloseable {

    actual suspend fun requestDevice(descriptor: DeviceDescriptor): Device? {
        return JniInterface.instance.wgpuAdapterRequestDevice(
            handler,
            descriptor
        ).let { Device(it) }
    }

    actual override fun close() {
        JniInterface.instance.wgpuAdapterRelease(handler)
    }

}