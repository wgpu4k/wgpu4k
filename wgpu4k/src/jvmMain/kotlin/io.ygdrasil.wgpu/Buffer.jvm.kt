package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.panama.WGPUBufferMapAsyncCallback
import webgpu.WGPUBuffer
import webgpu.wgpuBufferGetMapState
import webgpu.wgpuBufferGetMappedRange
import webgpu.wgpuBufferGetSize
import webgpu.wgpuBufferGetUsage
import webgpu.wgpuBufferMapAsync
import webgpu.wgpuBufferRelease
import webgpu.wgpuBufferUnmap
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

private val mapCallback = WGPUBufferMapAsyncCallback.allocate({ status, data -> }, Arena.global())

actual class Buffer(internal val handler: WGPUBuffer) : AutoCloseable {

    actual val size: GPUSize64
        get() = wgpuBufferGetSize(handler)
    actual val usage: Set<BufferUsage>
        get() = wgpuBufferGetUsage(handler)
            .let { usage -> BufferUsage.entries.filter { it.value and usage != 0 }.toSet() }
    actual val mapState: BufferMapState
        get() = wgpuBufferGetMapState(handler)
            .let { BufferMapState.of(it) ?: error("Can't get map state: $it") }

    actual fun unmap() {
        wgpuBufferUnmap(handler)
    }

    actual fun mapFrom(buffer: ShortArray, offset: ULong) {
        wgpuBufferGetMappedRange(handler, offset, (buffer.size * Short.SIZE_BYTES).toULong())
            .copyFrom(MemorySegment.ofArray(buffer))
    }

    actual fun mapFrom(buffer: FloatArray, offset: ULong) {
        wgpuBufferGetMappedRange(handler, offset, (buffer.size * Float.SIZE_BYTES).toULong())
            .copyFrom(MemorySegment.ofArray(buffer))
    }

    actual fun mapFrom(buffer: ByteArray, offset: ULong) {
        wgpuBufferGetMappedRange(handler, offset, (buffer.size * Byte.SIZE_BYTES).toULong())
            .copyFrom(MemorySegment.ofArray(buffer))
    }

    actual suspend fun map(mode: Set<MapMode>, offset: GPUSize64, size: GPUSize64) {
        wgpuBufferMapAsync(handler, mode.toFlagInt(), offset, size, mapCallback, MemorySegment.NULL)
    }

    actual fun mapInto(buffer: ByteArray, offset: Int) {
        MemorySegment.ofArray(buffer)
            .copyFrom(
                wgpuBufferGetMappedRange(handler, offset.toLong(), buffer.size.toLong())
                    // create a slice, as the buffer size is wrong
                    .asSlice(0, buffer.size.toLong())
            )
    }

    actual fun mapInto(buffer: IntArray, offset: Int) {
        MemorySegment.ofArray(buffer)
            .copyFrom(
                wgpuBufferGetMappedRange(handler, offset.toLong(), (buffer.size * Int.SIZE_BYTES).toLong())
                    // create a slice, as the buffer size is wrong
                    .asSlice(0, buffer.size.toLong() * Int.SIZE_BYTES)
            )
    }

    actual override fun close() {
        wgpuBufferRelease(handler)
    }

}


