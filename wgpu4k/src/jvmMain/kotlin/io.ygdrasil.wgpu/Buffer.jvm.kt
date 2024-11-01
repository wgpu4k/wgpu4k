package io.ygdrasil.wgpu

import ffi.MemoryBuffer
import ffi.NativeAddress
import ffi.memoryScope
import webgpu.WGPUBuffer
import webgpu.WGPUBufferMapCallback
import webgpu.WGPUBufferMapCallbackInfo
import webgpu.WGPUMapAsyncStatus
import webgpu.WGPUStringView
import webgpu.wgpuBufferGetMapState
import webgpu.wgpuBufferGetMappedRange
import webgpu.wgpuBufferGetSize
import webgpu.wgpuBufferGetUsage
import webgpu.wgpuBufferMapAsync
import webgpu.wgpuBufferRelease
import webgpu.wgpuBufferUnmap

actual class Buffer(internal val handler: WGPUBuffer) : AutoCloseable {

    actual val size: GPUSize64
        get() = wgpuBufferGetSize(handler)
    actual val usage: Set<BufferUsage>
        get() = wgpuBufferGetUsage(handler)
            .let { usage -> BufferUsage.entries.filter { it.value.toULong() and usage != 0uL }.toSet() }
    actual val mapState: BufferMapState
        get() = wgpuBufferGetMapState(handler)
            .let { BufferMapState.of(it) ?: error("Can't get map state: $it") }

    actual fun unmap() {
        wgpuBufferUnmap(handler)
    }

    actual fun mapFrom(buffer: ShortArray, offset: ULong) {
        val bufferSize = (buffer.size * Short.SIZE_BYTES).toULong()
        wgpuBufferGetMappedRange(handler, offset, bufferSize)
            .asBuffer(bufferSize)
            .writeShorts(buffer)
    }

    actual fun mapFrom(buffer: FloatArray, offset: ULong) {
        val bufferSize = (buffer.size * Float.SIZE_BYTES).toULong()
        wgpuBufferGetMappedRange(handler, offset, bufferSize)
            .asBuffer(bufferSize)
            .writeFloats(buffer)
    }

    actual fun mapFrom(buffer: ByteArray, offset: ULong) {
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
                println("mapped")
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

    actual override fun close() {
        wgpuBufferRelease(handler)
    }

}

private fun NativeAddress?.asBuffer(size: ULong): MemoryBuffer =
    MemoryBuffer((this ?: error("buffer should not be null")), size)


