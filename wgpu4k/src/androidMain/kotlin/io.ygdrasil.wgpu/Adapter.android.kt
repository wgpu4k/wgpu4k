package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jna.WGPUSupportedLimits
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.mapper.map
import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k

actual class Adapter(val handler: Long) : AutoCloseable {

    actual val features: Set<Feature> by lazy {
        Feature.entries
            .mapNotNull { feature ->
                feature.takeIf { NativeWgpu4k.wgpuAdapterHasFeature(handler, feature.value) == 1 }
            }
            .toSet()
    }

    actual val limits: SupportedLimits by lazy {
        scoped { arena ->
            val supportedLimits = WGPUSupportedLimits.allocate(arena)
            NativeWgpu4k.wgpuAdapterGetLimits(handler, supportedLimits.pointer.toAddress())
            map(WGPUSupportedLimits.limits(supportedLimits))
        }
    }

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