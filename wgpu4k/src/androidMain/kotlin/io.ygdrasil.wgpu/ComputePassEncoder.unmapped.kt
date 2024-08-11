package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class ComputePassEncoder(internal val handler: Long) : AutoCloseable {

    actual fun setPipeline(pipeline: ComputePipeline) {
        JniInterface.instance.wgpuComputePassEncoderSetPipeline(handler, pipeline.handler)
    }

    actual fun dispatchWorkgroups(workgroupCountX: GPUSize32, workgroupCountY: GPUSize32, workgroupCountZ: GPUSize32) {
        JniInterface.instance.wgpuComputePassEncoderDispatchWorkgroups(
            handler,
            workgroupCountX,
            workgroupCountY,
            workgroupCountZ
        )
    }

    actual fun dispatchWorkgroupsIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64) {
        JniInterface.instance.wgpuComputePassEncoderDispatchWorkgroupsIndirect(
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
        JniInterface.instance.wgpuComputePassEncoderEnd(handler)
    }

    actual override fun close() {
        JniInterface.instance.wgpuComputePassEncoderRelease(handler)
    }
}