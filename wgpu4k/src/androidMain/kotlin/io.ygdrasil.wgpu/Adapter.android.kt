package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class Adapter(internal val handler: Long) : AutoCloseable {

    actual suspend fun requestDevice(descriptor: DeviceDescriptor): Device? {
        return JniInterfaceV2.wgpuAdapterRequestDevice(
            handler,
            descriptor
        ).takeIf { it != 0L }
            ?.let { Device(it) }
    }

    actual override fun close() {
        JniInterfaceV2.wgpuAdapterRelease(handler)
    }

}