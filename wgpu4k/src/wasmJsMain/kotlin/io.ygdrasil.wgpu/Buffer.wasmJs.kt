package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUBuffer

actual class Buffer(internal val handler: GPUBuffer) : AutoCloseable {

    actual val size: GPUSize64
        get() = handler.size
    actual val usage: Set<BufferUsage>
        get() = BufferUsage.entries.filter { it.value and handler.usage != 0 }.toSet()
    actual val mapState: BufferMapState
        get() = BufferMapState.of(handler.mapState) ?: error("fail to get MapState")

    actual fun unmap() {
        TODO("Not yet implemented")
    }

    actual fun mapFrom(buffer: FloatArray, offset: Int) {
        TODO("Not yet implemented")
    }

    actual fun mapFrom(buffer: ByteArray, offset: Int) {
        TODO("Not yet implemented")
    }

    actual fun mapInto(buffer: ByteArray, offset: Int) {
        TODO("Not yet implemented")
    }

    actual suspend fun map(mode: Set<MapMode>, offset: GPUSize64, size: GPUSize64) {
        TODO("Not yet implemented")
    }

    actual override fun close() {
        // Nothing to do on JS
    }
}