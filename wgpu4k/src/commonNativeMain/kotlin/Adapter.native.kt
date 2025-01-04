package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUAdapter
import io.ygdrasil.wgpu.WGPUAdapterRequestDeviceCallback
import io.ygdrasil.wgpu.WGPUDevice
import io.ygdrasil.wgpu.WGPUSupportedLimits
import io.ygdrasil.wgpu.wgpuAdapterGetLimits
import io.ygdrasil.wgpu.wgpuAdapterHasFeature
import io.ygdrasil.wgpu.wgpuAdapterRelease
import io.ygdrasil.wgpu.wgpuAdapterRequestDevice

actual class Adapter(internal val handler: WGPUAdapter) : AutoCloseable {

    actual val features: Set<FeatureName> by lazy {
        FeatureName.entries
            .mapNotNull { feature ->
                feature.takeIf { wgpuAdapterHasFeature(handler, feature.value) }
            }
            .toSet()
    }

    actual val limits: Limits = memoryScope { scope ->
        val supportedLimits = WGPUSupportedLimits.allocate(scope)
        wgpuAdapterGetLimits(handler, supportedLimits)
        val test: Limits = map(supportedLimits.limits)
        test
    }

    actual suspend fun requestDevice(descriptor: DeviceDescriptor): Device? = memoryScope { scope ->
        var fetchedDevice: WGPUDevice? = null

        val callback = WGPUAdapterRequestDeviceCallback.allocate(scope) { status, device, message, userdata ->
            if (status != 1u && device == null) error("fail to get device")
            fetchedDevice = device
        }

        wgpuAdapterRequestDevice(handler, null, callback, scope.bufferOfAddress(callback.handler).handler)

        fetchedDevice?.let(::Device) ?: error("fail to get device")

    }

    actual override fun close() {
        wgpuAdapterRelease(handler)
    }
}

