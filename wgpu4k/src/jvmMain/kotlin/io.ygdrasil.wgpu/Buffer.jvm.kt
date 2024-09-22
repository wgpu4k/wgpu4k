package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.panama.WGPUBufferMapAsyncCallback
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

private val mapCallback = WGPUBufferMapAsyncCallback.allocate({ status, data -> }, Arena.global())

actual class Buffer(internal val handler: MemorySegment) : AutoCloseable {

    actual val size: GPUSize64
        get() = wgpu_h.wgpuBufferGetSize(handler)
    actual val usage: Set<BufferUsage>
        get() = wgpu_h.wgpuBufferGetUsage(handler)
            .let { usage -> BufferUsage.entries.filter { it.value and usage != 0 }.toSet() }
    actual val mapState: BufferMapState
        get() = wgpu_h.wgpuBufferGetMapState(handler)
            .let { BufferMapState.of(it) ?: error("Can't get map state: $it") }

    actual fun unmap() {
        wgpu_h.wgpuBufferUnmap(handler)
    }

    actual fun mapFrom(buffer: ShortArray, offset: Int) {
        wgpu_h.wgpuBufferGetMappedRange(handler, offset.toLong(), (buffer.size * Short.SIZE_BYTES).toLong())
            .copyFrom(MemorySegment.ofArray(buffer))
    }

    actual fun mapFrom(buffer: FloatArray, offset: Int) {
        wgpu_h.wgpuBufferGetMappedRange(handler, offset.toLong(), (buffer.size * Float.SIZE_BYTES).toLong())
            .copyFrom(MemorySegment.ofArray(buffer))
    }

    actual fun mapFrom(buffer: ByteArray, offset: Int) {
        wgpu_h.wgpuBufferGetMappedRange(handler, offset.toLong(), (buffer.size * Byte.SIZE_BYTES).toLong())
            .copyFrom(MemorySegment.ofArray(buffer))
    }

    actual suspend fun map(mode: Set<MapMode>, offset: GPUSize64, size: GPUSize64) {
        wgpu_h.wgpuBufferMapAsync(handler, mode.toFlagInt(), offset, size, mapCallback, MemorySegment.NULL)
    }

    actual fun mapInto(buffer: ByteArray, offset: Int) {
        MemorySegment.ofArray(buffer)
            .copyFrom(
                wgpu_h.wgpuBufferGetMappedRange(handler, offset.toLong(), buffer.size.toLong())
                    // create a slice, as the buffer size is wrong
                    .asSlice(0, buffer.size.toLong())
            )
    }

    actual fun mapInto(buffer: IntArray, offset: Int) {
        MemorySegment.ofArray(buffer)
            .copyFrom(
                wgpu_h.wgpuBufferGetMappedRange(handler, offset.toLong(), (buffer.size * Int.SIZE_BYTES).toLong())
                    // create a slice, as the buffer size is wrong
                    .asSlice(0, buffer.size.toLong() * Int.SIZE_BYTES)
            )
    }

    actual override fun close() {
        wgpu_h.wgpuBufferRelease(handler)
    }

}


