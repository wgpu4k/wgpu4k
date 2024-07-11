package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUComputePassEncoder
import io.ygdrasil.wgpu.internal.js.toJsArray
import io.ygdrasil.wgpu.internal.js.toJsNumber
import org.khronos.webgl.Uint32Array

actual class ComputePassEncoder(internal val handler: GPUComputePassEncoder) : AutoCloseable {

    actual fun setPipeline(pipeline: ComputePipeline) = handler.setPipeline(pipeline.handler)

    actual fun dispatchWorkgroups(workgroupCountX: GPUSize32, workgroupCountY: GPUSize32, workgroupCountZ: GPUSize32) =
        handler.dispatchWorkgroups(workgroupCountX, workgroupCountY, workgroupCountZ)

    actual fun dispatchWorkgroupsIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64) =
        handler.dispatchWorkgroupsIndirect(indirectBuffer.handler, indirectOffset.toJsNumber())

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup?, dynamicOffsets: Array<GPUBufferDynamicOffset>) =
        handler.setBindGroup(index, bindGroup?.handler, dynamicOffsets.map { it.toJsNumber() }.toJsArray())

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup?) = handler.setBindGroup(index, bindGroup?.handler)

    actual fun setBindGroup(
        index: GPUIndex32,
        bindGroup: BindGroup?,
        dynamicOffsetsData: UIntArray,
        dynamicOffsetsDataStart: GPUSize64,
        dynamicOffsetsDataLength: GPUSize32
    ) {
        handler.setBindGroup(
            index,
            bindGroup?.handler,
            Uint32Array(dynamicOffsetsData.map { it.toJsNumber() }.toJsArray()),
            dynamicOffsetsDataStart.toJsNumber(),
            dynamicOffsetsDataLength
        )
    }

    actual fun end() = handler.end()

    actual override fun close() {
        // Nothing to do on JS
    }
}