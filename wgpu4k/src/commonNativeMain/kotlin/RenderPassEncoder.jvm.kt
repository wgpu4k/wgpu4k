package io.ygdrasil.wgpu

import ffi.ArrayHolder
import ffi.memoryScope
import io.ygdrasil.wgpu.mapper.map
import webgpu.WGPURenderPassEncoder
import webgpu.wgpuRenderPassEncoderBeginOcclusionQuery
import webgpu.wgpuRenderPassEncoderDraw
import webgpu.wgpuRenderPassEncoderDrawIndexed
import webgpu.wgpuRenderPassEncoderDrawIndexedIndirect
import webgpu.wgpuRenderPassEncoderEnd
import webgpu.wgpuRenderPassEncoderEndOcclusionQuery
import webgpu.wgpuRenderPassEncoderExecuteBundles
import webgpu.wgpuRenderPassEncoderRelease
import webgpu.wgpuRenderPassEncoderSetBindGroup
import webgpu.wgpuRenderPassEncoderSetBlendConstant
import webgpu.wgpuRenderPassEncoderSetIndexBuffer
import webgpu.wgpuRenderPassEncoderSetPipeline
import webgpu.wgpuRenderPassEncoderSetScissorRect
import webgpu.wgpuRenderPassEncoderSetStencilReference
import webgpu.wgpuRenderPassEncoderSetVertexBuffer
import webgpu.wgpuRenderPassEncoderSetViewport

actual class RenderPassEncoder(private val handler: WGPURenderPassEncoder) {

    actual fun end() {
        wgpuRenderPassEncoderEnd(handler)
        close()
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        wgpuRenderPassEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    ) {
        wgpuRenderPassEncoderDraw(handler, vertexCount, instanceCount, firstVertex, firstInstance)
    }

    actual fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32,
    ) {
        wgpuRenderPassEncoderDrawIndexed(handler, indexCount, instanceCount, firstIndex, baseVertex, firstInstance)
    }

    actual fun drawIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64) {
        wgpuRenderPassEncoderDrawIndexedIndirect(handler, indirectBuffer.handler, indirectOffset)
    }

    actual fun drawIndexedIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64) {
        wgpuRenderPassEncoderDrawIndexedIndirect(handler, indirectBuffer.handler, indirectOffset)
    }

    actual fun setBindGroup(index: UInt, bindGroup: BindGroup, dynamicOffsets: List<UInt>) = memoryScope { scope ->
        wgpuRenderPassEncoderSetBindGroup(
            handler,
            index,
            bindGroup.handler,
            dynamicOffsets.size.toULong(),
            scope.map(dynamicOffsets)
        )
    }

    actual fun setVertexBuffer(slot: UInt, buffer: Buffer) {
        wgpuRenderPassEncoderSetVertexBuffer(
            handler,
            slot,
            buffer.handler,
            0u,
            buffer.size
        )
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        wgpuRenderPassEncoderSetIndexBuffer(handler, buffer.handler, indexFormat.uValue, offset, size)
    }

    actual fun executeBundles(bundles: List<RenderBundle>) = memoryScope { scope ->
        wgpuRenderPassEncoderExecuteBundles(
            handler,
            bundles.size.toULong(),
            scope.bufferOfAddresses(bundles.map { it.handler.handler })
                .handler
                .let { ArrayHolder(it) }
        )
    }

    actual fun setViewport(x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float) {
        wgpuRenderPassEncoderSetViewport(
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
        wgpuRenderPassEncoderSetScissorRect(handler, x, y, width, height)
    }

    actual fun setBlendConstant(color: Color) = memoryScope { scope ->
        wgpuRenderPassEncoderSetBlendConstant(handler, scope.map(color))
    }

    actual fun setStencilReference(reference: GPUStencilValue) {
        wgpuRenderPassEncoderSetStencilReference(handler, reference)
    }

    actual fun beginOcclusionQuery(queryIndex: GPUSize32) {
        wgpuRenderPassEncoderBeginOcclusionQuery(handler, queryIndex)
    }

    actual fun endOcclusionQuery() {
        wgpuRenderPassEncoderEndOcclusionQuery(handler)
    }

    private fun close() {
        wgpuRenderPassEncoderRelease(handler)
    }

}