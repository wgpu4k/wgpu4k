@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

expect class ComputePassEncoder : AutoCloseable {

    fun setPipeline(pipeline: ComputePipeline)
    fun dispatchWorkgroups(workgroupCountX: GPUSize32, workgroupCountY: GPUSize32, workgroupCountZ: GPUSize32)
    fun dispatchWorkgroupsIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64)

    fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup?, dynamicOffsets: Array<GPUBufferDynamicOffset>)
    fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup?)
    fun setBindGroup(
        index: GPUIndex32,
        bindGroup: BindGroup?,
        dynamicOffsetsData: UIntArray,
        dynamicOffsetsDataStart: GPUSize64,
        dynamicOffsetsDataLength: GPUSize32
    )

    fun end()

}

data class ComputePassDescriptor(
    var label: String? = null,
    var timestampWrites: ComputePassTimestampWrites? = null
) {
    data class ComputePassTimestampWrites(
        var querySet: GPUQuerySet,
        var beginningOfPassWriteIndex: GPUSize32? = null,
        var endOfPassWriteIndex: GPUSize32? = null,
    )
}