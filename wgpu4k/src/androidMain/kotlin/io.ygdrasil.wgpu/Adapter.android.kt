package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class Adapter(val handler: Long) : AutoCloseable {

    actual suspend fun requestDevice(descriptor: DeviceDescriptor): Device? {
        return JniInterface.wgpuAdapterRequestDevice(
            handler,
            descriptor
        ).takeIf { it != 0L }
            ?.let { Device(it) }
    }

    actual override fun close() {
        JniInterface.wgpuAdapterRelease(handler)
    }

}