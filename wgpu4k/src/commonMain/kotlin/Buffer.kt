package io.ygdrasil.webgpu

expect class Buffer : GPUBuffer {

    override val size: GPUSize64Out
    override val usage: GPUBufferUsageFlags
    override val mapState: GPUBufferMapState
    override var label: String

    override suspend fun mapAsync(
        mode: GPUMapModeFlags,
        offset: GPUSize64,
        size: GPUSize64?
    ) : Result<Unit>

    override fun getMappedRange(
        offset: GPUSize64,
        size: GPUSize64?
    ): ArrayBuffer

    override fun unmap()
    override fun close()

}
