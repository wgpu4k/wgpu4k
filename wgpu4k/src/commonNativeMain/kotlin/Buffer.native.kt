package io.ygdrasil.webgpu

import ffi.MemoryBuffer
import ffi.NativeAddress
import ffi.memoryScope
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.wgpu.WGPUBuffer
import io.ygdrasil.wgpu.WGPUBufferMapAsyncCallback
import io.ygdrasil.wgpu.WGPUBufferMapAsyncStatus
import io.ygdrasil.wgpu.wgpuBufferGetMapState
import io.ygdrasil.wgpu.wgpuBufferGetMappedRange
import io.ygdrasil.wgpu.wgpuBufferGetSize
import io.ygdrasil.wgpu.wgpuBufferGetUsage
import io.ygdrasil.wgpu.wgpuBufferMapAsync
import io.ygdrasil.wgpu.wgpuBufferRelease
import io.ygdrasil.wgpu.wgpuBufferUnmap

private val logger = KotlinLogging.logger {}

actual class Buffer(internal val handler: WGPUBuffer) : AutoCloseable {

    actual val size: GPUSize64
        get() = wgpuBufferGetSize(handler)
    actual val usage: Set<BufferUsage>
        get() = wgpuBufferGetUsage(handler)
            .let { usage -> BufferUsage.entries.filter { it.value.toUInt() and usage != 0u }.toSet() }
    actual val mapState: BufferMapState
        get() = wgpuBufferGetMapState(handler)
            .let { BufferMapState.of(it) ?: error("Can't get map state: $it") }

    actual fun unmap() {
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
        val callback = WGPUBufferMapAsyncCallback.allocate(scope) { status: WGPUBufferMapAsyncStatus, userdata: NativeAddress? ->
            logger.info { "mapped" }
        }

        wgpuBufferMapAsync(
            handler,
            mode.toFlagUInt(),
            offset,
            size,
            callback,
            scope.bufferOfAddress(callback.handler).handler
        )
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

    actual override fun close() {
        wgpuBufferRelease(handler)
    }

}

private fun NativeAddress?.asBuffer(size: ULong): MemoryBuffer =
    MemoryBuffer((this ?: error("buffer should not be null")), size)


