package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import io.ygdrasil.wgpu.mapper.map
import java.lang.foreign.MemorySegment

actual class RenderBundleEncoder(internal val handler: MemorySegment) : AutoCloseable {
    actual fun finish(descriptor: RenderBundleDescriptor): RenderBundle  = confined { arena ->
        arena.map(descriptor)
            .let { wgpu_h.wgpuRenderBundleEncoderFinish(handler, it) }
            .let(::RenderBundle)
    }

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup){
        wgpu_h.wgpuRenderBundleEncoderSetBindGroup(handler, index, bindGroup.handler,0, MemorySegment.NULL)
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        wgpu_h.wgpuRenderBundleEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun setVertexBuffer(slot: GPUIndex32, buffer: Buffer, offset: GPUSize64, size: GPUSize64) {
        wgpu_h.wgpuRenderBundleEncoderSetVertexBuffer(handler, slot, buffer.handler, offset, size)
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        wgpu_h.wgpuRenderBundleEncoderSetIndexBuffer(handler, buffer.handler, indexFormat.value, offset, size)
    }

    actual fun drawIndexed(indexCount: GPUSize32, instanceCount: GPUSize32, firstIndex: GPUSize32, baseVertex: GPUSignedOffset32, firstInstance: GPUSize32) {
        wgpu_h.wgpuRenderBundleEncoderDrawIndexed(handler, indexCount, instanceCount, firstIndex, baseVertex, firstInstance)
    }

    actual fun draw(vertexCount: GPUSize32, instanceCount: GPUSize32, firstVertex: GPUSize32, firstInstance: GPUSize32) {
        wgpu_h.wgpuRenderBundleEncoderDraw(handler, vertexCount, instanceCount, firstVertex, firstInstance)
    }

    actual override fun close() {
        wgpu_h.wgpuRenderBundleEncoderRelease(handler)
    }
}