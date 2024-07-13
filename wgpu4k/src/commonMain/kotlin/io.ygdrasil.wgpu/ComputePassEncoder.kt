package io.ygdrasil.wgpu

expect class ComputePassEncoder : AutoCloseable {

    fun setPipeline(pipeline: ComputePipeline)
    fun dispatchWorkgroups(workgroupCountX: GPUSize32, workgroupCountY: GPUSize32 = 1, workgroupCountZ: GPUSize32 = 1)
    fun dispatchWorkgroupsIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64)

    fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup?, dynamicOffsets: Array<GPUBufferDynamicOffset>)
    fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup?)
    fun setBindGroup(
        index: GPUIndex32,
        bindGroup: BindGroup?,
        dynamicOffsetsData: UIntArray,
        dynamicOffsetsDataStart: GPUSize64,
        dynamicOffsetsDataLength: GPUSize32,
    )

    fun end()

    override fun close()
}

data class ComputePassDescriptor(
    val label: String? = null,
    val timestampWrites: ComputePassTimestampWrites? = null,
) {
    data class ComputePassTimestampWrites(
        val querySet: QuerySet,
        val beginningOfPassWriteIndex: GPUSize32? = null,
        val endOfPassWriteIndex: GPUSize32? = null,
    )
}