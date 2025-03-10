package io.ygdrasil.webgpu

expect class Buffer : GPUBuffer {

    override val size: GPUSize64Out
    override val usage: GPUFlagsConstant
    override val mapState: GPUBufferMapState
    override var label: String

    fun mapFrom(buffer: ShortArray, offset: GPUSize64 = 0u)

    fun mapFrom(buffer: FloatArray, offset: GPUSize64 = 0u)

    fun mapFrom(buffer: ByteArray, offset: GPUSize64 = 0u)

    fun mapInto(buffer: ByteArray, offset: GPUSize64 = 0u)

    fun mapInto(buffer: IntArray, offset: GPUSize64 = 0u)

    suspend fun map(mode: Set<MapMode>, offset: GPUSize64 = 0u, size: GPUSize64 = this.size)

    override suspend fun mapAsync(
        mode: GPUMapModeFlags,
        offset: GPUSize64,
        size: GPUSize64
    )

    override fun getMappedRange(
        offset: GPUSize64,
        size: GPUSize64
    ): ArrayBuffer

    override fun unmap()
    override fun close()

}
