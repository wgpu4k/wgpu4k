package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface

actual class ComputePassEncoder(val handler: Long) : AutoCloseable {

    actual fun setPipeline(pipeline: ComputePipeline) {
        JnaInterface.wgpuComputePassEncoderSetPipeline(handler, pipeline.handler)
    }

    actual fun dispatchWorkgroups(workgroupCountX: GPUSize32, workgroupCountY: GPUSize32, workgroupCountZ: GPUSize32) {
        JnaInterface.wgpuComputePassEncoderDispatchWorkgroups(
            handler,
            workgroupCountX,
            workgroupCountY,
            workgroupCountZ
        )
    }

    actual fun dispatchWorkgroupsIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64) {
        JnaInterface.wgpuComputePassEncoderDispatchWorkgroupsIndirect(
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
        JnaInterface.wgpuComputePassEncoderEnd(handler)
    }

    actual override fun close() {
        JnaInterface.wgpuComputePassEncoderRelease(handler)
    }
}