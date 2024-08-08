package io.ygdrasil.wgpu

actual class Buffer(internal val handler: Long) : AutoCloseable {

    actual val size: GPUSize64
        get() = TODO("Not yet implemented")
    actual val usage: Set<BufferUsage>
        get() = TODO("Not yet implemented")
    actual val mapState: BufferMapState
        get() = TODO("Not yet implemented")

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
        TODO("Not yet implemented")
    }
}