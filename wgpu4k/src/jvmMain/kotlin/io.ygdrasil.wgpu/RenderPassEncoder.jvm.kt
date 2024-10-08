package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import io.ygdrasil.wgpu.internal.jvm.toPointerArray
import java.lang.foreign.MemorySegment

actual class RenderPassEncoder(private val handler: MemorySegment) {

    actual fun end() {
        wgpu_h.wgpuRenderPassEncoderEnd(handler)
        close()
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        wgpu_h.wgpuRenderPassEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    ) {
        wgpu_h.wgpuRenderPassEncoderDraw(handler, vertexCount, instanceCount, firstVertex, firstInstance)
    }

    actual fun setBindGroup(index: Int, bindGroup: BindGroup) {
        wgpu_h.wgpuRenderPassEncoderSetBindGroup(
            handler,
            index,
            bindGroup.handler,
            0L,
            MemorySegment.NULL
        )
    }

    actual fun setVertexBuffer(slot: Int, buffer: Buffer) {
        wgpu_h.wgpuRenderPassEncoderSetVertexBuffer(
            handler,
            slot,
            buffer.handler,
            0L,
            buffer.size
        )
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        wgpu_h.wgpuRenderPassEncoderSetIndexBuffer(handler, buffer.handler, indexFormat.value, offset, size)
    }
    actual fun executeBundles(bundles: List<RenderBundle>) = confined { arena ->
        wgpu_h.wgpuRenderPassEncoderExecuteBundles(
            handler,
            bundles.size.toLong(),
            bundles.map { it.handler }.toPointerArray(arena)
        )
    }

    private fun close() {
        wgpu_h.wgpuRenderPassEncoderRelease(handler)
    }

}