package io.ygdrasil.webgpu

actual class ComputePassEncoder(internal val handler: WGPUComputePassEncoder) : GPUComputePassEncoder {
    actual override var label: String
        get() = handler.label
        set(value) {
            handler.label = value
        }

    actual override fun setPipeline(pipeline: GPUComputePipeline) = handler.setPipeline((pipeline as ComputePipeline).handler)

    actual override fun dispatchWorkgroups(
        workgroupCountX: GPUSize32,
        workgroupCountY: GPUSize32,
        workgroupCountZ: GPUSize32
    ) = handler.dispatchWorkgroups(
        workgroupCountX.asJsNumber(),
        workgroupCountY.asJsNumber(),
        workgroupCountZ.asJsNumber()
    )

    actual override fun dispatchWorkgroupsIndirect(indirectBuffer: GPUBuffer, indirectOffset: GPUSize64) =
        handler.dispatchWorkgroupsIndirect((indirectBuffer as Buffer).handler, indirectOffset)

    actual override fun pushDebugGroup(groupLabel: String) {
        TODO("Not yet implemented")
    }

    actual override fun popDebugGroup() {
        TODO("Not yet implemented")
    }

    actual override fun insertDebugMarker(markerLabel: String) {
        TODO("Not yet implemented")
    }

    actual override fun setBindGroup(
        index: GPUIndex32,
        bindGroup: GPUBindGroup?,
        dynamicOffsetsData: List<UInt>
    ) {
        handler.setBindGroup(index, bindGroup, dynamicOffsetsData)
    }

    actual override fun end() = handler.end()
}