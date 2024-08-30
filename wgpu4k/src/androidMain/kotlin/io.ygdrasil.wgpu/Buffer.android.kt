package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.JniInterface
import java.lang.foreign.MemorySegment

actual class Buffer(val handler: Long) : AutoCloseable {

    internal val mhandler = MemorySegment(Pointer(handler), 0L)

    actual val size: GPUSize64
        get() = JniInterface.wgpuBufferGetSize(handler)
    actual val usage: Set<BufferUsage>
        get() = JniInterface.wgpuBufferGetUsage(handler)
            .let { usage -> BufferUsage.entries.filter { it.value and usage != 0 }.toSet() }
    actual val mapState: BufferMapState
        get() = JniInterface.wgpuBufferGetMapState(handler)
            .let { BufferMapState.of(it) ?: error("Can't get map state: $it") }

    actual fun unmap() {
        JniInterface.wgpuBufferUnmap(handler)
    }

    actual fun mapFrom(buffer: FloatArray, offset: Int) {
        JniInterface.mapFrom(buffer, offset)
    }

    actual fun mapFrom(buffer: ByteArray, offset: Int) {
        JniInterface.mapFrom(buffer, offset)
    }

    actual suspend fun map(mode: Set<MapMode>, offset: GPUSize64, size: GPUSize64) {
        JniInterface.wgpuBufferMapAsync(handler, mode.toFlagInt(), offset, size)
    }

    actual fun mapInto(buffer: ByteArray, offset: Int) {
        JniInterface.wgpuBufferMapInto(handler, buffer, offset)
    }

    actual override fun close() {
        JniInterface.wgpuBufferRelease(handler)
    }

}