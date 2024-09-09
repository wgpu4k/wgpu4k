package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.mapper.map
import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k

actual class Adapter(val handler: Long) : AutoCloseable {

    actual suspend fun requestDevice(descriptor: DeviceDescriptor): Device? = scoped { arena ->
        NativeWgpu4k.wgpuAdapterRequestDeviceNoCallback(
            handler,
            arena.map(descriptor).pointer.toAddress()
        ).takeIf { it != 0L }
            ?.let { Device(it) }
    }

    actual override fun close() {
        NativeWgpu4k.wgpuAdapterRelease(handler)
    }

}