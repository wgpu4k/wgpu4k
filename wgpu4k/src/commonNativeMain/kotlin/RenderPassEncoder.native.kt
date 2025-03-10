package io.ygdrasil.webgpu

import ffi.ArrayHolder
import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPURenderPassEncoder
import io.ygdrasil.wgpu.wgpuRenderPassEncoderBeginOcclusionQuery
import io.ygdrasil.wgpu.wgpuRenderPassEncoderDraw
import io.ygdrasil.wgpu.wgpuRenderPassEncoderDrawIndexed
import io.ygdrasil.wgpu.wgpuRenderPassEncoderDrawIndexedIndirect
import io.ygdrasil.wgpu.wgpuRenderPassEncoderEnd
import io.ygdrasil.wgpu.wgpuRenderPassEncoderEndOcclusionQuery
import io.ygdrasil.wgpu.wgpuRenderPassEncoderExecuteBundles
import io.ygdrasil.wgpu.wgpuRenderPassEncoderRelease
import io.ygdrasil.wgpu.wgpuRenderPassEncoderSetBindGroup
import io.ygdrasil.wgpu.wgpuRenderPassEncoderSetBlendConstant
import io.ygdrasil.wgpu.wgpuRenderPassEncoderSetIndexBuffer
import io.ygdrasil.wgpu.wgpuRenderPassEncoderSetPipeline
import io.ygdrasil.wgpu.wgpuRenderPassEncoderSetScissorRect
import io.ygdrasil.wgpu.wgpuRenderPassEncoderSetStencilReference
import io.ygdrasil.wgpu.wgpuRenderPassEncoderSetVertexBuffer
import io.ygdrasil.wgpu.wgpuRenderPassEncoderSetViewport

actual class RenderPassEncoder(private val handler: WGPURenderPassEncoder) : GPURenderPassEncoder {

    actual override fun end() {
        wgpuRenderPassEncoderEnd(handler)
        close()
    }

    actual override fun setPipeline(pipeline: GPURenderPipeline) {
        wgpuRenderPassEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual override fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    ) {
        wgpuRenderPassEncoderDraw(handler, vertexCount, instanceCount, firstVertex, firstInstance)
    }

    actual override fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32,
    ) {
        wgpuRenderPassEncoderDrawIndexed(handler, indexCount, instanceCount, firstIndex, baseVertex, firstInstance)
    }

    actual override fun drawIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64) {
        wgpuRenderPassEncoderDrawIndexedIndirect(handler, indirectBuffer.handler, indirectOffset)
    }

    actual override fun drawIndexedIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64) {
        wgpuRenderPassEncoderDrawIndexedIndirect(handler, indirectBuffer.handler, indirectOffset)
    }

    actual override fun setBindGroup(index: UInt, bindGroup: GPUBindGroup, dynamicOffsets: List<UInt>) = memoryScope { scope ->
        wgpuRenderPassEncoderSetBindGroup(
            handler,
            index,
            (bindGroup as BindGroup).handler,
            dynamicOffsets.size.toULong(),
            scope.map(dynamicOffsets)
        )
    }

    actual override fun setVertexBuffer(slot: UInt, buffer: Buffer) {
        wgpuRenderPassEncoderSetVertexBuffer(
            handler,
            slot,
            buffer.handler,
            0u,
            buffer.size
        )
    }

    actual override fun setIndexBuffer(buffer: Buffer, indexFormat: GPUIndexFormat, offset: GPUSize64, size: GPUSize64) {
        wgpuRenderPassEncoderSetIndexBuffer(handler, buffer.handler, indexFormat.value, offset, size)
    }

    actual override fun executeBundles(bundles: List<GPURenderBundle>) = memoryScope { scope ->
        wgpuRenderPassEncoderExecuteBundles(
            handler,
            bundles.size.toULong(),
            scope.bufferOfAddresses(bundles.map { (it as RenderBundle).handler.handler })
                .handler
                .let { ArrayHolder(it) }
        )
    }

    actual override fun setViewport(x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float) {
        wgpuRenderPassEncoderSetViewport(
            handler,
            x, y, width, height, minDepth, maxDepth
        )
    }

    actual override fun setScissorRect(
        x: GPUIntegerCoordinate,
        y: GPUIntegerCoordinate,
        width: GPUIntegerCoordinate,
        height: GPUIntegerCoordinate,
    ) {
        wgpuRenderPassEncoderSetScissorRect(handler, x, y, width, height)
    }

    actual override fun setBlendConstant(color: GPUColor) = memoryScope { scope ->
        wgpuRenderPassEncoderSetBlendConstant(handler, scope.map(color))
    }

    actual override fun setStencilReference(reference: GPUStencilValue) {
        wgpuRenderPassEncoderSetStencilReference(handler, reference)
    }

    actual override fun beginOcclusionQuery(queryIndex: GPUSize32) {
        wgpuRenderPassEncoderBeginOcclusionQuery(handler, queryIndex)
    }

    actual override fun endOcclusionQuery() {
        wgpuRenderPassEncoderEndOcclusionQuery(handler)
    }

    private fun close() {
        wgpuRenderPassEncoderRelease(handler)
    }

}