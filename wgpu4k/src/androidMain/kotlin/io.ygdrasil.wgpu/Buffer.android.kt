package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.JnaInterface
import java.lang.foreign.MemorySegment

actual class Buffer(val handler: Long) : AutoCloseable {

    internal val mhandler = MemorySegment(Pointer(handler), 0L)

    actual val size: GPUSize64
        get() = JnaInterface.wgpuBufferGetSize(handler)
    actual val usage: Set<BufferUsage>
        get() = JnaInterface.wgpuBufferGetUsage(handler)
            .let { usage -> BufferUsage.entries.filter { it.value and usage != 0 }.toSet() }
    actual val mapState: BufferMapState
        get() = JnaInterface.wgpuBufferGetMapState(handler)
            .let { BufferMapState.of(it) ?: error("Can't get map state: $it") }

    actual fun unmap() {
        JnaInterface.wgpuBufferUnmap(handler)
    }

    actual fun mapFrom(buffer: FloatArray, offset: Int) {
        JnaInterface.wgpuBufferGetMappedRange(handler, offset.toLong(), (buffer.size * Float.SIZE_BYTES).toLong())
            .let(::Pointer)
            .write(0, buffer, 0, buffer.size)
    }

    actual fun mapFrom(buffer: ByteArray, offset: Int) {
        JnaInterface.wgpuBufferGetMappedRange(handler, offset.toLong(), (buffer.size * Float.SIZE_BYTES).toLong())
            .let(::Pointer)
            .write(0, buffer, 0, buffer.size)
    }

    actual suspend fun map(mode: Set<MapMode>, offset: GPUSize64, size: GPUSize64) {
        JnaInterface.wgpuBufferMapAsync(handler, mode.toFlagInt(), offset, size, 0L, 0L)
    }

    actual fun mapInto(buffer: ByteArray, offset: Int) {
        JnaInterface.wgpuBufferGetMappedRange(handler, offset.toLong(), buffer.size.toLong())
            .let(::Pointer)
            .read(0, buffer, 0, buffer.size)
    }

    actual fun mapInto(buffer: IntArray, offset: Int) {
        JnaInterface.wgpuBufferGetMappedRange(handler, offset.toLong(), (buffer.size * Int.SIZE_BYTES).toLong())
            .let(::Pointer)
            .read(0, buffer, 0, buffer.size)
    }

    actual override fun close() {
        JnaInterface.wgpuBufferRelease(handler)
    }

}