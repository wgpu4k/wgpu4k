package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class Buffer(val handler: Long) : AutoCloseable {

    actual val size: GPUSize64
        get() = JniInterface.instance.wgpuBufferGetSize(handler)
    actual val usage: Set<BufferUsage>
        get() = JniInterface.instance.wgpuBufferGetUsage(handler)
            .let { usage -> BufferUsage.entries.filter { it.value and usage != 0 }.toSet() }
    actual val mapState: BufferMapState
        get() = JniInterface.instance.wgpuBufferGetMapState(handler)
            .let { BufferMapState.of(it) ?: error("Can't get map state: $it") }

    actual fun unmap() {
        JniInterface.instance.wgpuBufferUnmap(handler)
    }

    actual fun mapFrom(buffer: FloatArray, offset: Int) {
        JniInterface.instance.mapFrom(buffer, offset)
    }

    actual fun mapFrom(buffer: ByteArray, offset: Int) {
        JniInterface.instance.mapFrom(buffer, offset)
    }

    actual suspend fun map(mode: Set<MapMode>, offset: GPUSize64, size: GPUSize64) {
        JniInterface.instance.wgpuBufferMapAsync(handler, mode.toFlagInt(), offset, size)
    }

    actual fun mapInto(buffer: ByteArray, offset: Int) {
        JniInterface.instance.wgpuBufferMapInto(handler, buffer, offset)
    }

    actual override fun close() {
        JniInterface.instance.wgpuBufferRelease(handler)
    }

}