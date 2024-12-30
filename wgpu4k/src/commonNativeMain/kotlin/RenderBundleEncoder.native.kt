package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import webgpu.WGPURenderBundleEncoder
import webgpu.wgpuRenderBundleEncoderDraw
import webgpu.wgpuRenderBundleEncoderDrawIndexed
import webgpu.wgpuRenderBundleEncoderFinish
import webgpu.wgpuRenderBundleEncoderRelease
import webgpu.wgpuRenderBundleEncoderSetBindGroup
import webgpu.wgpuRenderBundleEncoderSetIndexBuffer
import webgpu.wgpuRenderBundleEncoderSetPipeline
import webgpu.wgpuRenderBundleEncoderSetVertexBuffer

actual class RenderBundleEncoder(internal val handler: WGPURenderBundleEncoder) : AutoCloseable {

    actual fun finish(descriptor: RenderBundleDescriptor): RenderBundle = memoryScope { scope ->
        scope.map(descriptor)
            .let { wgpuRenderBundleEncoderFinish(handler, it) }
            ?.let(::RenderBundle) ?: error("fail to create render bundle")
    }

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup) {
        wgpuRenderBundleEncoderSetBindGroup(handler, index, bindGroup.handler, 0u, null)
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        wgpuRenderBundleEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun setVertexBuffer(slot: GPUIndex32, buffer: Buffer, offset: GPUSize64, size: GPUSize64) {
        wgpuRenderBundleEncoderSetVertexBuffer(handler, slot, buffer.handler, offset, size)
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        wgpuRenderBundleEncoderSetIndexBuffer(handler, buffer.handler, indexFormat.value, offset, size)
    }

    actual fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32
    ) {
        wgpuRenderBundleEncoderDrawIndexed(handler, indexCount, instanceCount, firstIndex, baseVertex, firstInstance)
    }

    actual fun draw(
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