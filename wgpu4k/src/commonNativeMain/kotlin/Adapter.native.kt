package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUAdapter
import io.ygdrasil.wgpu.WGPULimits
import io.ygdrasil.wgpu.WGPURequestDeviceCallback
import io.ygdrasil.wgpu.WGPURequestDeviceCallbackInfo
import io.ygdrasil.wgpu.WGPURequestDeviceStatus_Success
import io.ygdrasil.wgpu.wgpuAdapterGetLimits
import io.ygdrasil.wgpu.wgpuAdapterHasFeature
import io.ygdrasil.wgpu.wgpuAdapterRelease
import io.ygdrasil.wgpu.wgpuAdapterRequestDevice
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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

    actual override suspend fun requestDevice(descriptor: GPUDeviceDescriptor?): Result<GPUDevice> = suspendCoroutine { continuation ->
        memoryScope { scope ->

            val callback = WGPURequestDeviceCallback.allocate(scope) { status, device, message, userdata1, userdata2 ->
                continuation.resume(when(status) {
                    WGPURequestDeviceStatus_Success -> when (device) {
                        null -> Result.failure(IllegalStateException("Device is null"))
                        else -> Result.success(Device(device))
                    }
                    else -> Result.failure(IllegalStateException("request Device fail with status: $status and message: ${message?.data?.toKString(message.length)}"))
                })
            }

            val callbackInfo = WGPURequestDeviceCallbackInfo.allocate(scope).apply {
                this.callback = callback
                this.userdata2 = scope.bufferOfAddress(callback.handler).handler
            }

            wgpuAdapterRequestDevice(handler, descriptor?.let { scope.map(it) }, callbackInfo)
        }
    }

    actual override fun close() {
        wgpuAdapterRelease(handler)
    }
}

