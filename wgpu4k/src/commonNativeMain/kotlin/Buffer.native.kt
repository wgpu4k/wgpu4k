package io.ygdrasil.webgpu

import ffi.MemoryBuffer
import ffi.NativeAddress
import ffi.memoryScope
import io.github.oshai.kotlinlogging.KotlinLogging
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
import io.ygdrasil.wgpu.wgpuBufferUnmap
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private val logger = KotlinLogging.logger {}

actual class Buffer(internal val handler: WGPUBuffer) : GPUBuffer {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}
    actual override val size: GPUSize64
        get() = wgpuBufferGetSize(handler)
    actual override val usage: GPUBufferUsageFlags
        get() = wgpuBufferGetUsage(handler)
            .let { usage -> GPUBufferUsage.entries.filter { it.value and usage != 0uL }.toSet() }
    actual override val mapState: GPUBufferMapState
        get() = wgpuBufferGetMapState(handler)
            .let { GPUBufferMapState.of(it) ?: error("Can't get map state: $it") }

    actual override fun unmap() {
        wgpuBufferUnmap(handler)
    }

    actual override fun getMappedRange(offset: GPUSize64, size: GPUSize64): ArrayBuffer {
        TODO("Not yet implemented")
    }

    actual override suspend fun mapAsync(mode: GPUMapModeFlags, offset: GPUSize64, size: GPUSize64): Result<Unit> =
        suspendCoroutine { continuation ->
            memoryScope { scope ->
                val callback = WGPUBufferMapCallback.allocate(scope, object : WGPUBufferMapCallback {
                    override fun invoke(
                        status: WGPUMapAsyncStatus,
                        message: WGPUStringView?,
                        userdata1: NativeAddress?,
                        userdata2: NativeAddress?
                    ) {
                        logger.info { "mapped" }
                        continuation.resume(when(status) {
                            WGPUMapAsyncStatus_Success -> Result.success(Unit)
                            else -> Result.failure(IllegalStateException("map fail with status: $status and message: ${message?.data?.toKString(message.length)}"))
                        })
                    }

                })
                val bufferCallbackInfo = WGPUBufferMapCallbackInfo.allocate(scope).also {
                    it.callback = callback
                    it.userdata2 = callback.handler
                }
                wgpuBufferMapAsync(handler, mode.toFlagULong(), offset, size, bufferCallbackInfo)
            }
        }

    actual override fun close() {
        wgpuBufferRelease(handler)
    }
}

private fun NativeAddress?.asBuffer(size: ULong): MemoryBuffer =
    MemoryBuffer((this ?: error("buffer should not be null")), size)

actual fun GPUBuffer.mapFrom(buffer: ShortArray, offset: GPUSize64) {
    val bufferSize = (buffer.size * Short.SIZE_BYTES).toULong()
    wgpuBufferGetMappedRange((this as Buffer).handler, offset, bufferSize)
        .asBuffer(bufferSize)
        .writeShorts(buffer)
}

actual fun GPUBuffer.mapFrom(buffer: FloatArray, offset: GPUSize64) {
    val bufferSize = (buffer.size * Float.SIZE_BYTES).toULong()
    wgpuBufferGetMappedRange((this as Buffer).handler, offset, bufferSize)
        .asBuffer(bufferSize)
        .writeFloats(buffer)
}

actual fun GPUBuffer.mapFrom(buffer: ByteArray, offset: GPUSize64) {
    val bufferSize = (buffer.size * Byte.SIZE_BYTES).toULong()
    wgpuBufferGetMappedRange((this as Buffer).handler, offset, bufferSize)
        .asBuffer(bufferSize)
        .writeBytes(buffer)
}

actual fun GPUBuffer.mapInto(buffer: ByteArray, offset: ULong) {
    val bufferSize = (buffer.size * Byte.SIZE_BYTES).toULong()
    wgpuBufferGetMappedRange((this as Buffer).handler, offset, bufferSize)
        .asBuffer(bufferSize)
        .readBytes(buffer)
}

actual fun GPUBuffer.mapInto(buffer: IntArray, offset: ULong) {
    val bufferSize = (buffer.size * Int.SIZE_BYTES).toULong()
    wgpuBufferGetMappedRange((this as Buffer).handler, offset, bufferSize)
        .asBuffer(bufferSize)
        .readInts(buffer)
}