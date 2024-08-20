package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class Buffer(val handler: Long) : AutoCloseable {

    actual val size: GPUSize64
        get() = JniInterfaceV2.wgpuBufferGetSize(handler)
    actual val usage: Set<BufferUsage>
        get() = JniInterfaceV2.wgpuBufferGetUsage(handler)
            .let { usage -> BufferUsage.entries.filter { it.value and usage != 0 }.toSet() }
    actual val mapState: BufferMapState
        get() = JniInterfaceV2.wgpuBufferGetMapState(handler)
            .let { BufferMapState.of(it) ?: error("Can't get map state: $it") }

    actual fun unmap() {
        JniInterfaceV2.wgpuBufferUnmap(handler)
    }

    actual fun mapFrom(buffer: FloatArray, offset: Int) {
        JniInterfaceV2.mapFrom(buffer, offset)
    }

    actual fun mapFrom(buffer: ByteArray, offset: Int) {
        JniInterfaceV2.mapFrom(buffer, offset)
    }

    actual suspend fun map(mode: Set<MapMode>, offset: GPUSize64, size: GPUSize64) {
        JniInterfaceV2.wgpuBufferMapAsync(handler, mode.toFlagInt(), offset, size)
    }

    actual fun mapInto(buffer: ByteArray, offset: Int) {
        JniInterfaceV2.wgpuBufferMapInto(handler, buffer, offset)
    }

    actual override fun close() {
        JniInterfaceV2.wgpuBufferRelease(handler)
    }

}