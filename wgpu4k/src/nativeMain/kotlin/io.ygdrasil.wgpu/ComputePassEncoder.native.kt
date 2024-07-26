@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.WGPUComputePassEncoder

actual class ComputePassEncoder(internal val handler: WGPUComputePassEncoder) : AutoCloseable {

    actual fun setPipeline(pipeline: ComputePipeline) {
        TODO("Not yet implemented")
    }

    actual fun dispatchWorkgroups(workgroupCountX: GPUSize32, workgroupCountY: GPUSize32, workgroupCountZ: GPUSize32) {
        TODO("Not yet implemented")
    }

    actual fun dispatchWorkgroupsIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64) {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    actual override fun close() {
        TODO("Not yet implemented")
    }
}