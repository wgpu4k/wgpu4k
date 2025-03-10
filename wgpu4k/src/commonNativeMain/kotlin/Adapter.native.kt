package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUAdapter
import io.ygdrasil.wgpu.WGPUDevice
import io.ygdrasil.wgpu.WGPULimits
import io.ygdrasil.wgpu.WGPURequestDeviceCallback
import io.ygdrasil.wgpu.WGPURequestDeviceCallbackInfo
import io.ygdrasil.wgpu.wgpuAdapterGetLimits
import io.ygdrasil.wgpu.wgpuAdapterHasFeature
import io.ygdrasil.wgpu.wgpuAdapterRelease
import io.ygdrasil.wgpu.wgpuAdapterRequestDevice

actual class Adapter(internal val handler: WGPUAdapter) : GPUAdapter {

    actual override val info: GPUAdapterInfo
        get() = TODO("Not yet implemented")
    actual override val isFallbackAdapter: Boolean
        get() = TODO("Not yet implemented")

    actual override val features: Set<GPUFeatureName> by lazy {
        GPUFeatureName.entries
            .mapNotNull { feature ->
                feature.takeIf { wgpuAdapterHasFeature(handler, feature.value) }
            }
            .toSet()
    }

    actual override val limits: GPUSupportedLimits = memoryScope { scope ->
        val supportedLimits = WGPULimits.allocate(scope)
        wgpuAdapterGetLimits(handler, supportedLimits)
        val test: Limits = map(supportedLimits)
        test
    }

    actual suspend fun requestDevice(descriptor: GPUDeviceDescriptor): Device? = memoryScope { scope ->
        var fetchedDevice: WGPUDevice? = null

        val callback = WGPURequestDeviceCallback.allocate(scope) { status, device, message, userdata1, userdata2 ->
            if (status != 1u && device == null) error("fail to get device")
            fetchedDevice = device
        }

        val callbackInfo = WGPURequestDeviceCallbackInfo.allocate(scope).apply {
            this.callback = callback
            this.userdata2 = scope.bufferOfAddress(callback.handler).handler
        }

        wgpuAdapterRequestDevice(handler, null, callbackInfo)

        fetchedDevice?.let(::Device) ?: error("fail to get device")

    }

    actual override fun close() {
        wgpuAdapterRelease(handler)
    }
}

