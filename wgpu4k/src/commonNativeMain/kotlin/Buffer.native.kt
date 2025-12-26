package io.ygdrasil.webgpu

import ffi.NativeAddress
import ffi.memoryScope
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.webgpu.mapper.toArrayBuffer
import io.ygdrasil.wgpu.WGPUBuffer
import io.ygdrasil.wgpu.WGPUBufferMapCallback
import io.ygdrasil.wgpu.WGPUBufferMapCallbackInfo
import io.ygdrasil.wgpu.WGPUMapAsyncStatus
import io.ygdrasil.wgpu.WGPUMapAsyncStatus_Success
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuBufferGetMapState
import io.ygdrasil.wgpu.wgpuBufferGetMappedRange
import io.ygdrasil.wgpu.wgpuBufferGetSize
import io.ygdrasil.wgpu.wgpuBufferGetUsage
import io.ygdrasil.wgpu.wgpuBufferMapAsync
import io.ygdrasil.wgpu.wgpuBufferRelease
import io.ygdrasil.wgpu.wgpuBufferSetLabel
import io.ygdrasil.wgpu.wgpuBufferUnmap
import io.ygdrasil.wgpu.wgpuDevicePoll
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private val logger = KotlinLogging.logger {}

actual class Buffer(val handler: WGPUBuffer, private val device: Device, label: String) : GPUBuffer {

    actual override var label: String = label
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuBufferSetLabel(handler, newLabel)
            field = value
        }
    actual override val size: GPUSize64
        get() = wgpuBufferGetSize(handler)
    actual override val usage: Set<GPUBufferUsage>
        get() = wgpuBufferGetUsage(handler)
            .let { usage -> GPUBufferUsage.entries.filter { it.value and usage != 0uL }.toSet() }
    actual override val mapState: GPUBufferMapState
        get() = wgpuBufferGetMapState(handler)
            .let { GPUBufferMapState.of(it) ?: error("Can't get map state: $it") }

    actual override fun unmap() {
        wgpuBufferUnmap(handler)
    }

    actual override fun getMappedRange(offset: GPUSize64, size: GPUSize64?): ArrayBuffer {
        val size = size ?: (this.size - offset)
        return wgpuBufferGetMappedRange(handler, offset, size)
            ?.toArrayBuffer(size) ?: error("Can't get mapped range")
    }

    actual override suspend fun mapAsync(mode: GPUMapMode, offset: GPUSize64, size: GPUSize64?): Result<Unit> =
        suspendCoroutine { continuation ->
            val size = size ?: (this.size - offset)
            memoryScope { scope ->
                val callback = WGPUBufferMapCallback.allocate(scope, object : WGPUBufferMapCallback {
                    override fun invoke(
                        status: WGPUMapAsyncStatus,
                        message: WGPUStringView?,
                        userdata1: NativeAddress?,
                        userdata2: NativeAddress?
                    ) {
                        logger.info { "mapped" }
                        continuation.resume(
                            when (status) {
                                WGPUMapAsyncStatus_Success -> Result.success(Unit)
                                else -> Result.failure(
                                    IllegalStateException(
                                        "map fail with status: $status and message: ${
                                            message?.data?.toKString(
                                                message.length
                                            )
                                        }"
                                    )
                                )
                            }
                        )
                    }

                })
                val bufferCallbackInfo = WGPUBufferMapCallbackInfo.allocate(scope).also {
                    it.callback = callback
                    it.userdata2 = callback.handler
                }
                wgpuBufferMapAsync(handler, mode.value, offset, size, bufferCallbackInfo)
                wgpuDevicePoll(device.handler, true, null)
            }
        }

    actual override fun close() {
        wgpuBufferRelease(handler)
    }
}
