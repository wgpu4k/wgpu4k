package io.ygdrasil.webgpu

expect class Buffer : AutoCloseable {

    val size: GPUSize64
    val usage: Set<BufferUsage>
    val mapState: BufferMapState

    fun unmap()

    fun mapFrom(buffer: ShortArray, offset: GPUSize64 = 0u)

    fun mapFrom(buffer: FloatArray, offset: GPUSize64 = 0u)

    fun mapFrom(buffer: ByteArray, offset: GPUSize64 = 0u)

    fun mapInto(buffer: ByteArray, offset: GPUSize64 = 0u)

    fun mapInto(buffer: IntArray, offset: GPUSize64 = 0u)

    suspend fun map(mode: Set<MapMode>, offset: GPUSize64 = 0u, size: GPUSize64 = this.size)

    override fun close()
}

data class BufferDescriptor(
    val size: GPUSize64,
    val usage: Set<BufferUsage>,
    val mappedAtCreation: Boolean = false,
    val label: String? = null,
)