package io.ygdrasil.webgpu

@WGPULowLevelApi
expect class ComputePassEncoder : GPUComputePassEncoder {

    override var label: String

    override fun setPipeline(pipeline: GPUComputePipeline)
    override fun dispatchWorkgroups(
        workgroupCountX: GPUSize32,
        workgroupCountY: GPUSize32,
        workgroupCountZ: GPUSize32
    )

    override fun dispatchWorkgroupsIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: GPUSize64
    )

    override fun end()
    override fun pushDebugGroup(groupLabel: String)
    override fun popDebugGroup()
    override fun insertDebugMarker(markerLabel: String)
    override fun setBindGroup(
        index: GPUIndex32,
        bindGroup: GPUBindGroup?,
        dynamicOffsetsData: List<UInt>
    )

}
