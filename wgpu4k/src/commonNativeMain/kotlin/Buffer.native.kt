package io.ygdrasil.webgpu

import ffi.MemoryBuffer
import ffi.NativeAddress
import ffi.memoryScope
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.wgpu.WGPUBuffer
import io.ygdrasil.wgpu.WGPUBufferMapCallback
import io.ygdrasil.wgpu.WGPUBufferMapCallbackInfo
import io.ygdrasil.wgpu.WGPUMapAsyncStatus
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuBufferGetMapState
import io.ygdrasil.wgpu.wgpuBufferGetMappedRange
import io.ygdrasil.wgpu.wgpuBufferGetSize
import io.ygdrasil.wgpu.wgpuBufferGetUsage
import io.ygdrasil.wgpu.wgpuBufferMapAsync
import io.ygdrasil.wgpu.wgpuBufferRelease
import io.ygdrasil.wgpu.wgpuBufferUnmap

private val logger = KotlinLogging.logger {}

actual class Buffer(internal val handler: WGPUBuffer) : GPUBuffer {

    override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override val size: GPUSize64
        get() = wgpuBufferGetSize(handler)
    override val usage: Set<GPUBufferUsage>
        get() = wgpuBufferGetUsage(handler)
            .let { usage -> BufferUsage.entries.filter { it.value and usage != 0uL }.toSet() }
    override val mapState: GPUBufferMapState
        get() = wgpuBufferGetMapState(handler)
            .let { GPUBufferMapState.of(it) ?: error("Can't get map state: $it") }

    override fun unmap() {
        wgpuBufferUnmap(handler)
    }

    actual fun mapFrom(buffer: ShortArray, offset: GPUSize64) {
        val bufferSize = (buffer.size * Short.SIZE_BYTES).toULong()
        wgpuBufferGetMappedRange(handler, offset, bufferSize)
            .asBuffer(bufferSize)
            .writeShorts(buffer)
    }

    actual fun mapFrom(buffer: FloatArray, offset: GPUSize64) {
        val bufferSize = (buffer.size * Float.SIZE_BYTES).toULong()
        wgpuBufferGetMappedRange(handler, offset, bufferSize)
            .asBuffer(bufferSize)
            .writeFloats(buffer)
    }

    actual fun mapFrom(buffer: ByteArray, offset: GPUSize64) {
        val bufferSize = (buffer.size * Byte.SIZE_BYTES).toULong()
        wgpuBufferGetMappedRange(handler, offset, bufferSize)
            .asBuffer(bufferSize)
            .writeBytes(buffer)
    }

    actual suspend fun map(mode: Set<MapMode>, offset: GPUSize64, size: GPUSize64) = memoryScope { scope ->
        val callback = WGPUBufferMapCallback.allocate(scope, object : WGPUBufferMapCallback {
            override fun invoke(
                status: WGPUMapAsyncStatus,
                message: WGPUStringView?,
                userdata1: NativeAddress?,
                userdata2: NativeAddress?
            ) {
                logger.info { "mapped" }
            }

        })
        val bufferCallbackInfo = WGPUBufferMapCallbackInfo.allocate(scope).also {
            it.callback = callback
            it.userdata2 = callback.handler
        }
        wgpuBufferMapAsync(handler, mode.toFlagULong(), offset, size, bufferCallbackInfo)
    }

    actual fun mapInto(buffer: ByteArray, offset: ULong) {
        val bufferSize = (buffer.size * Byte.SIZE_BYTES).toULong()
        wgpuBufferGetMappedRange(handler, offset, bufferSize)
            .asBuffer(bufferSize)
            .readBytes(buffer)
    }

    actual fun mapInto(buffer: IntArray, offset: ULong) {
        val bufferSize = (buffer.size * Int.SIZE_BYTES).toULong()
        wgpuBufferGetMappedRange(handler, offset, bufferSize)
            .asBuffer(bufferSize)
            .readInts(buffer)
    }

    override fun close() {
        wgpuBufferRelease(handler)
    }

    override fun getMappedRange(offset: GPUSize64, size: GPUSize64): ArrayBuffer {
        TODO("Not yet implemented")
    }

    override suspend fun mapAsync(mode: GPUMapModeFlags, offset: GPUSize64, size: GPUSize64) {
        TODO("Not yet implemented")
    }

}

private fun NativeAddress?.asBuffer(size: ULong): MemoryBuffer =
    MemoryBuffer((this ?: error("buffer should not be null")), size)
