package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUComputePassEncoder
import io.ygdrasil.wgpu.wgpuComputePassEncoderDispatchWorkgroups
import io.ygdrasil.wgpu.wgpuComputePassEncoderDispatchWorkgroupsIndirect
import io.ygdrasil.wgpu.wgpuComputePassEncoderEnd
import io.ygdrasil.wgpu.wgpuComputePassEncoderSetPipeline

actual class ComputePassEncoder(internal val handler: WGPUComputePassEncoder) : GPUComputePassEncoder {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override fun setPipeline(pipeline: GPUComputePipeline) {
        wgpuComputePassEncoderSetPipeline(handler, (pipeline as ComputePipeline).handler)
    }

    actual override fun dispatchWorkgroups(workgroupCountX: GPUSize32, workgroupCountY: GPUSize32, workgroupCountZ: GPUSize32) {
        wgpuComputePassEncoderDispatchWorkgroups(
            handler,
            workgroupCountX,
            workgroupCountY,
            workgroupCountZ
        )
    }

    actual override fun dispatchWorkgroupsIndirect(indirectBuffer: GPUBuffer, indirectOffset: GPUSize64) {
        wgpuComputePassEncoderDispatchWorkgroupsIndirect(
            handler,
            (indirectBuffer as Buffer).handler,
            indirectOffset
        )
    }

    actual override fun end() {
        wgpuComputePassEncoderEnd(handler)
    }

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
        dynamicOffsetsData: List<UInt>,
        dynamicOffsetsDataStart: GPUSize64,
        dynamicOffsetsDataLength: GPUSize32
    ) {
        TODO("Not yet implemented")
    }

}