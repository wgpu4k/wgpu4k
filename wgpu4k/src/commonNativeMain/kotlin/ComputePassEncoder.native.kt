package io.ygdrasil.webgpu

import webgpu.WGPUComputePassEncoder
import webgpu.wgpuComputePassEncoderDispatchWorkgroups
import webgpu.wgpuComputePassEncoderDispatchWorkgroupsIndirect
import webgpu.wgpuComputePassEncoderEnd
import webgpu.wgpuComputePassEncoderRelease
import webgpu.wgpuComputePassEncoderSetPipeline

actual class ComputePassEncoder(internal val handler: WGPUComputePassEncoder) : AutoCloseable {

    actual fun setPipeline(pipeline: ComputePipeline) {
        wgpuComputePassEncoderSetPipeline(handler, pipeline.handler)
    }

    actual fun dispatchWorkgroups(workgroupCountX: GPUSize32, workgroupCountY: GPUSize32, workgroupCountZ: GPUSize32) {
        wgpuComputePassEncoderDispatchWorkgroups(
            handler,
            workgroupCountX,
            workgroupCountY,
            workgroupCountZ
        )
    }

    actual fun dispatchWorkgroupsIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64) {
        wgpuComputePassEncoderDispatchWorkgroupsIndirect(
            handler,
            indirectBuffer.handler,
            indirectOffset
        )
    }

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup?, dynamicOffsets: Array<GPUBufferDynamicOffset>) {
        TODO("Not yet implemented")
    }

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup?) {
        TODO("Not yet implemented")
    }

    actual fun setBindGroup(
        index: GPUIndex32,
        bindGroup: BindGroup?,
        dynamicOffsetsData: UIntArray,
        dynamicOffsetsDataStart: GPUSize64,
        dynamicOffsetsDataLength: GPUSize32
    ) {
        TODO("Not yet implemented")
    }

    actual fun end() {
        wgpuComputePassEncoderEnd(handler)
    }

    actual override fun close() {
        wgpuComputePassEncoderRelease(handler)
    }
}