package io.ygdrasil.wgpu

expect class Buffer : AutoCloseable {

    val size: GPUSize64
    val usage: Set<BufferUsage>
    val mapState: BufferMapState

    fun unmap()

    fun mapFrom(buffer: ShortArray, offset: Int = 0)

    fun mapFrom(buffer: FloatArray, offset: Int = 0)

    fun mapFrom(buffer: ByteArray, offset: Int = 0)

    fun mapInto(buffer: ByteArray, offset: Int = 0)

    fun mapInto(buffer: IntArray, offset: Int = 0)

    suspend fun map(mode: Set<MapMode>, offset: GPUSize64 = 0, size: GPUSize64 = this.size)

    override fun close()
}

data class BufferDescriptor(
    val size: GPUSize64,
    val usage: Set<BufferUsage>,
    val mappedAtCreation: Boolean = false,
    val label: String? = null,
)