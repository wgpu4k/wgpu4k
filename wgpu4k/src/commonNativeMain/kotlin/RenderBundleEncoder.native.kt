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

    actual override fun finish(descriptor: GPURenderBundleDescriptor?): GPURenderBundle = memoryScope { scope ->
        scope?.map(descriptor)
            .let { wgpuRenderBundleEncoderFinish(handler, it) }
            ?.let(::RenderBundle) ?: error("fail to create render bundle")
    }

    actual override fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup) {
        wgpuRenderBundleEncoderSetBindGroup(handler, index, bindGroup.handler, 0u, null)
    }

    actual override fun setPipeline(renderPipeline: RenderPipeline) {
        wgpuRenderBundleEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual override fun setVertexBuffer(slot: GPUIndex32, buffer: Buffer, offset: GPUSize64, size: GPUSize64) {
        wgpuRenderBundleEncoderSetVertexBuffer(handler, slot, buffer.handler, offset, size)
    }

    actual override fun setIndexBuffer(buffer: Buffer, indexFormat: GPUIndexFormat, offset: GPUSize64, size: GPUSize64) {
        wgpuRenderBundleEncoderSetIndexBuffer(handler, buffer.handler, indexFormat.value, offset, size)
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
}