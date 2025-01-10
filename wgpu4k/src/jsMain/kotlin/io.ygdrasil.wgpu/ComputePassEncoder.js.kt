package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUComputePassEncoder
import org.khronos.webgl.Uint32Array

actual class ComputePassEncoder(internal val handler: GPUComputePassEncoder) : AutoCloseable {

    actual fun setPipeline(pipeline: ComputePipeline) = handler.setPipeline(pipeline.handler)

    actual fun dispatchWorkgroups(workgroupCountX: GPUSize32, workgroupCountY: GPUSize32, workgroupCountZ: GPUSize32) =
        handler.dispatchWorkgroups(workgroupCountX, workgroupCountY, workgroupCountZ)

    actual fun dispatchWorkgroupsIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64) =
        handler.dispatchWorkgroupsIndirect(indirectBuffer.handler, indirectOffset)

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup?, dynamicOffsets: Array<GPUBufferDynamicOffset>) =
        handler.setBindGroup(index, bindGroup?.handler, dynamicOffsets.unsafeCast<Uint32Array>())

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup?) = handler.setBindGroup(index, bindGroup?.handler)
    actual fun setBindGroup(
        index: GPUIndex32,
        bindGroup: BindGroup?,
        dynamicOffsetsData: UIntArray,
        dynamicOffsetsDataStart: GPUSize64,
        dynamicOffsetsDataLength: GPUSize32,
    ) = handler.setBindGroup(
        index,
        bindGroup?.handler,
        dynamicOffsetsData.unsafeCast<Uint32Array>(),
        dynamicOffsetsDataStart,
        dynamicOffsetsDataLength
    )

    actual fun end() = handler.end()

    actual override fun close() {
        // Nothing to do on js
    }

}