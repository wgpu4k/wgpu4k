package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.WGPUComputePassEncoder
import io.ygdrasil.wgpu.internal.jvm.wgpuComputePassEncoderEnd
import io.ygdrasil.wgpu.internal.jvm.wgpuComputePassEncoderRelease

actual class ComputePassEncoder(internal val handler: WGPUComputePassEncoder) : AutoCloseable {

    actual fun setPipeline(pipeline: ComputePipeline) {
        TODO()
    }

    actual fun dispatchWorkgroups(workgroupCountX: GPUSize32, workgroupCountY: GPUSize32, workgroupCountZ: GPUSize32) {
        TODO()
    }

    actual fun dispatchWorkgroupsIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64) {
        TODO()
    }

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup?, dynamicOffsets: Array<GPUBufferDynamicOffset>) {
        TODO()
    }

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup?) {
        TODO()
    }

    actual fun setBindGroup(
        index: GPUIndex32,
        bindGroup: BindGroup?,
        dynamicOffsetsData: UIntArray,
        dynamicOffsetsDataStart: GPUSize64,
        dynamicOffsetsDataLength: GPUSize32
    ) {
        TODO()
    }

    actual fun end() {
        wgpuComputePassEncoderEnd(handler)
    }

    override fun close() {
        wgpuComputePassEncoderRelease(handler)
    }
}