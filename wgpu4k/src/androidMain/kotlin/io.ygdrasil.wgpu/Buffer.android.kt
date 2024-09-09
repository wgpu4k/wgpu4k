package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k
import java.lang.foreign.MemorySegment

actual class Buffer(val handler: Long) : AutoCloseable {

    internal val mhandler = MemorySegment(Pointer(handler), 0L)

    actual val size: GPUSize64
        get() = NativeWgpu4k.wgpuBufferGetSize(handler)
    actual val usage: Set<BufferUsage>
        get() = NativeWgpu4k.wgpuBufferGetUsage(handler)
            .let { usage -> BufferUsage.entries.filter { it.value and usage != 0 }.toSet() }
    actual val mapState: BufferMapState
        get() = NativeWgpu4k.wgpuBufferGetMapState(handler)
            .let { BufferMapState.of(it) ?: error("Can't get map state: $it") }

    actual fun unmap() {
        NativeWgpu4k.wgpuBufferUnmap(handler)
    }

    actual fun mapFrom(buffer: FloatArray, offset: Int) {
        NativeWgpu4k.wgpuBufferGetMappedRange(handler, offset.toLong(), (buffer.size * Float.SIZE_BYTES).toLong())
            .let(::Pointer)
            .write(0, buffer, 0, buffer.size)
    }

    actual fun mapFrom(buffer: ByteArray, offset: Int) {
        NativeWgpu4k.wgpuBufferGetMappedRange(handler, offset.toLong(), (buffer.size * Float.SIZE_BYTES).toLong())
            .let(::Pointer)
            .write(0, buffer, 0, buffer.size)
    }

    actual suspend fun map(mode: Set<MapMode>, offset: GPUSize64, size: GPUSize64) {
        NativeWgpu4k.wgpuBufferMapAsync(handler, mode.toFlagInt(), offset, size, 0L, 0L)
    }

    actual fun mapInto(buffer: ByteArray, offset: Int) {
        NativeWgpu4k.wgpuBufferGetMappedRange(handler, offset.toLong(), buffer.size.toLong())
            .let(::Pointer)
            .read(0, buffer, 0, buffer.size)
    }

    actual fun mapInto(buffer: IntArray, offset: Int) {
        NativeWgpu4k.wgpuBufferGetMappedRange(handler, offset.toLong(), (buffer.size * Int.SIZE_BYTES).toLong())
            .let(::Pointer)
            .read(0, buffer, 0, buffer.size)
    }

    actual override fun close() {
        NativeWgpu4k.wgpuBufferRelease(handler)
    }

}