package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import io.ygdrasil.wgpu.internal.jvm.toPointerArray
import io.ygdrasil.wgpu.mapper.map
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

    actual fun setViewport(x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float) {
        wgpu_h.wgpuRenderPassEncoderSetViewport(
            handler,
            x, y, width, height, minDepth, maxDepth
        )
    }

    actual fun setScissorRect(
        x: GPUIntegerCoordinate,
        y: GPUIntegerCoordinate,
        width: GPUIntegerCoordinate,
        height: GPUIntegerCoordinate,
    ) {
        wgpu_h.wgpuRenderPassEncoderSetScissorRect(handler, x, y, width, height)
    }

    actual fun setBlendConstant(color: Color) = confined { arena ->
        wgpu_h.wgpuRenderPassEncoderSetBlendConstant(handler, arena.map(color))
    }

    actual fun setStencilReference(reference: GPUStencilValue) {
        wgpu_h.wgpuRenderPassEncoderSetStencilReference(handler, reference.toInt())
    }

    actual fun beginOcclusionQuery(queryIndex: GPUSize32) {
        wgpu_h.wgpuRenderPassEncoderBeginOcclusionQuery(handler, queryIndex)
    }

    actual fun endOcclusionQuery() {
        wgpu_h.wgpuRenderPassEncoderEndOcclusionQuery(handler)
    }

    private fun close() {
        wgpu_h.wgpuRenderPassEncoderRelease(handler)
    }

}