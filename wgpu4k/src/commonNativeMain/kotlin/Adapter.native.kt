package io.ygdrasil.webgpu

import ffi.NativeAddress
import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import webgpu.WGPUAdapter
import webgpu.WGPUDevice
import webgpu.WGPULimits
import webgpu.WGPURequestDeviceCallback
import webgpu.WGPURequestDeviceCallbackInfo
import webgpu.WGPURequestDeviceStatus
import webgpu.WGPUStringView
import webgpu.wgpuAdapterGetLimits
import webgpu.wgpuAdapterHasFeature
import webgpu.wgpuAdapterRelease
import webgpu.wgpuAdapterRequestDevice

actual class Adapter(internal val handler: WGPUAdapter) : AutoCloseable {

    actual val features: Set<FeatureName> by lazy {
        FeatureName.entries
            .mapNotNull { feature ->
                feature.takeIf { wgpuAdapterHasFeature(handler, feature.value) }
            }
            .toSet()
    }

    actual val limits: Limits = memoryScope { scope ->
        val supportedLimits = WGPULimits.allocate(scope)
        wgpuAdapterGetLimits(handler, supportedLimits)
        val test: Limits = map(supportedLimits)
        test
    }

    actual suspend fun requestDevice(descriptor: DeviceDescriptor): Device? = memoryScope { scope ->
        var fetchedDevice: WGPUDevice? = null

        val callback = WGPURequestDeviceCallback.allocate(scope, object : WGPURequestDeviceCallback {

            override fun invoke(
                status: WGPURequestDeviceStatus,
                device: WGPUDevice?,
                message: WGPUStringView?,
                userdata1: NativeAddress?,
                userdata2: NativeAddress?
            ) {
                if (status != 1u && device == null) error("fail to get device")
                fetchedDevice = device
            }

        })

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

