package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPURenderBundleEncoder
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderDraw
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderDrawIndexed
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderFinish
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderRelease
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderSetBindGroup
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderSetIndexBuffer
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderSetPipeline
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderSetVertexBuffer

actual class RenderBundleEncoder(internal val handler: WGPURenderBundleEncoder) : GPURenderBundleEncoder {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override fun finish(descriptor: GPURenderBundleDescriptor?): GPURenderBundle = memoryScope { scope ->
        descriptor?.let {scope.map(descriptor) }
            .let { wgpuRenderBundleEncoderFinish(handler, it) }
            ?.let(::RenderBundle) ?: error("fail to create render bundle")
    }

    actual override fun setBindGroup(
        index: GPUIndex32,
        bindGroup: GPUBindGroup?,
        dynamicOffsetsData: List<UInt>,
        dynamicOffsetsDataStart: GPUSize64,
        dynamicOffsetsDataLength: GPUSize32
    ) = memoryScope { scope ->
        // TODO: use dynamicOffsetsDataStart and dynamicOffsetsDataLength
        wgpuRenderBundleEncoderSetBindGroup(
            handler,
            index,
            (bindGroup as BindGroup).handler,
            dynamicOffsetsData.size.toULong(),
            scope.map(dynamicOffsetsData)
        )
    }

    actual override fun setPipeline(pipeline: GPURenderPipeline) {
        wgpuRenderBundleEncoderSetPipeline(handler, (pipeline as RenderPipeline).handler)
    }

    actual override fun setVertexBuffer(slot: GPUIndex32, buffer: GPUBuffer?, offset: GPUSize64, size: GPUSize64) {
        wgpuRenderBundleEncoderSetVertexBuffer(handler, slot, buffer?.let { (buffer as Buffer).handler }, offset, size)
    }

    actual override fun setIndexBuffer(buffer: GPUBuffer, indexFormat: GPUIndexFormat, offset: GPUSize64, size: GPUSize64) {
        wgpuRenderBundleEncoderSetIndexBuffer(handler, (buffer as Buffer).handler, indexFormat.value, offset, size)
    }

    actual override fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32
    ) {
        wgpuRenderBundleEncoderDrawIndexed(handler, indexCount, instanceCount, firstIndex, baseVertex, firstInstance)
    }

    actual override fun drawIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: GPUSize64
    ) {
        TODO("Not yet implemented")
    }

    actual override fun drawIndexedIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: GPUSize64
    ) {
        TODO("Not yet implemented")
    }

    actual override fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    ) {
        wgpuRenderBundleEncoderDraw(handler, vertexCount, instanceCount, firstVertex, firstInstance)
    }

    actual override fun close() {
        wgpuRenderBundleEncoderRelease(handler)
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
}