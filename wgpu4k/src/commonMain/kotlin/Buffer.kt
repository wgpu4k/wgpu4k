package io.ygdrasil.webgpu

@WGPULowLevelApi
expect class Buffer : GPUBuffer {

    override val size: GPUSize64Out
    override val usage: Set<GPUBufferUsage>
    override val mapState: GPUBufferMapState
    override var label: String

    override suspend fun mapAsync(
        mode: GPUMapMode,
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
