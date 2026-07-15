@file:OptIn(
    kotlinx.coroutines.InternalCoroutinesApi::class,
    kotlin.concurrent.atomics.ExperimentalAtomicApi::class,
)

package io.ygdrasil.webgpu

import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.CallbackRegistration
import io.ygdrasil.kffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.webgpu.mapper.mapDeviceDescriptor
import io.ygdrasil.wgpu.WGPUAdapter
import io.ygdrasil.wgpu.WGPUAdapterInfo
import io.ygdrasil.wgpu.WGPUCallbackMode_AllowSpontaneous
import io.ygdrasil.wgpu.WGPULimits
import io.ygdrasil.wgpu.WGPURequestDeviceCallback
import io.ygdrasil.wgpu.WGPURequestDeviceCallbackInfo
import io.ygdrasil.wgpu.WGPURequestDeviceStatus_Success
import io.ygdrasil.wgpu.WGPUUncapturedErrorCallback
import io.ygdrasil.wgpu.allocate
import io.ygdrasil.wgpu.register
import io.ygdrasil.wgpu.toBoolean
import io.ygdrasil.wgpu.wgpuAdapterGetInfo
import io.ygdrasil.wgpu.wgpuAdapterGetLimits
import io.ygdrasil.wgpu.wgpuAdapterHasFeature
import io.ygdrasil.wgpu.wgpuAdapterRelease
import io.ygdrasil.wgpu.wgpuAdapterRequestDevice
import io.ygdrasil.wgpu.wgpuDeviceRelease
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.concurrent.atomics.AtomicBoolean

actual class Adapter(val handler: WGPUAdapter) : GPUAdapter {

    actual override val info: GPUAdapterInfo
        get() = memoryScope{ scope ->
            val info = WGPUAdapterInfo.allocate(scope)
            wgpuAdapterGetInfo(handler, info)
            map(info)
        }

    actual override val features: GPUSupportedFeatures by lazy {
        GPUFeatureName.entries
            .mapNotNull { feature ->
                feature.takeIf { wgpuAdapterHasFeature(handler, feature.value).toBoolean() }
            }
            .toSet()
    }

    actual override val limits: GPUSupportedLimits = memoryScope { scope ->
        val supportedLimits = WGPULimits.allocate(scope)
        wgpuAdapterGetLimits(handler, supportedLimits)
        map(supportedLimits)
    }

    actual override suspend fun requestDevice(
        descriptor: GPUDeviceDescriptor?,
    ): Result<GPUDevice> = suspendCancellableCoroutine { continuation ->
        val callbackClaimed = AtomicBoolean(false)
        var uncapturedErrorRegistration: CallbackRegistration<WGPUUncapturedErrorCallback>? = null
        lateinit var requestRegistration: CallbackRegistration<WGPURequestDeviceCallback>

        requestRegistration = WGPURequestDeviceCallback.register(
            policy = CallbackPolicy.ONCE,
            callback = WGPURequestDeviceCallback { status, device, message, _ ->
                if (!callbackClaimed.compareAndSet(expectedValue = false, newValue = true)) {
                    device?.let(::wgpuDeviceRelease)
                } else {
                    try {
                        if (status == WGPURequestDeviceStatus_Success && device != null) {
                            val ownedErrorRegistration = uncapturedErrorRegistration
                            uncapturedErrorRegistration = null
                            val wrapped = Device(
                                handler = device,
                                label = descriptor?.label.orEmpty(),
                                uncapturedErrorRegistration = ownedErrorRegistration,
                            )
                            val result = Result.success<GPUDevice>(wrapped)
                            val token = continuation.tryResume(result, idempotent = null) { _, rejected, _ ->
                                rejected.getOrNull()?.close()
                            }
                            if (token == null) wrapped.close() else continuation.completeResume(token)
                        } else {
                            device?.let(::wgpuDeviceRelease)
                            val text = message.data?.toKString(message.length)
                            val token = continuation.tryResume(
                                Result.failure<GPUDevice>(
                                    IllegalStateException(
                                        "requestDevice failed with status: $status and message: $text"
                                    )
                                )
                            )
                            if (token != null) continuation.completeResume(token)
                        }
                    } finally {
                        requestRegistration.close()
                        uncapturedErrorRegistration?.close()
                        uncapturedErrorRegistration = null
                    }
                }
            },
        )

        if (!continuation.isActive) {
            requestRegistration.close()
            return@suspendCancellableCoroutine
        }

        memoryScope { scope ->
            val mapped = descriptor?.let(scope::mapDeviceDescriptor)
            uncapturedErrorRegistration = mapped?.uncapturedErrorRegistration
            if (!continuation.isActive) {
                requestRegistration.close()
                uncapturedErrorRegistration?.close()
                uncapturedErrorRegistration = null
                return@memoryScope
            }
            val callbackInfo = WGPURequestDeviceCallbackInfo.allocate(
                scope,
                WGPUCallbackMode_AllowSpontaneous,
                requestRegistration,
            )
            wgpuAdapterRequestDevice(handler, mapped?.value, callbackInfo)
        }

        if (callbackClaimed.compareAndSet(expectedValue = false, newValue = true)) {
            requestRegistration.close()
            uncapturedErrorRegistration?.close()
            uncapturedErrorRegistration = null
            if (continuation.isActive) {
                val token = continuation.tryResume(
                    Result.failure<GPUDevice>(
                        IllegalStateException("requestDevice callback was not delivered inline")
                    )
                )
                if (token != null) continuation.completeResume(token)
            }
        }
    }

    actual override fun close() {
        wgpuAdapterRelease(handler)
    }
}
