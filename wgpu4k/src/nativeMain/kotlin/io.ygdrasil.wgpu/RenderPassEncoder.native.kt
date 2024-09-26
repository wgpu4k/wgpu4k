@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.toPointerArray
import io.ygdrasil.wgpu.mapper.map
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import webgpu.WGPURenderPassEncoder
import webgpu.wgpuRenderPassEncoderBeginOcclusionQuery
import webgpu.wgpuRenderPassEncoderDraw
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

actual class RenderPassEncoder(internal val handler: WGPURenderPassEncoder) {

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
        wgpuRenderPassEncoderDraw(
            handler,
            vertexCount.toUInt(),
            instanceCount.toUInt(),
            firstVertex.toUInt(),
            firstInstance.toUInt()
        )
    }

    actual fun setBindGroup(index: Int, bindGroup: BindGroup) {
        wgpuRenderPassEncoderSetBindGroup(
            handler,
            index.toUInt(),
            bindGroup.handler,
            0uL,
            null
        )
    }

    actual fun setVertexBuffer(slot: Int, buffer: Buffer) {
        wgpuRenderPassEncoderSetVertexBuffer(
            handler,
            slot.toUInt(),
            buffer.handler,
            0uL,
            buffer.size.toULong()
        )
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        wgpuRenderPassEncoderSetIndexBuffer(
            handler,
            buffer.handler,
            indexFormat.value.toUInt(),
            offset.toULong(),
            size.toULong()
        )
    }

    actual fun executeBundles(bundles: List<RenderBundle>) = memScoped {
        wgpuRenderPassEncoderExecuteBundles(
            handler,
            bundles.size.toULong(),
            bundles.map { it.handler }.toPointerArray(this)
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
        wgpuRenderPassEncoderSetScissorRect(handler, x.toUInt(), y.toUInt(), width.toUInt(), height.toUInt())
    }

    actual fun setBlendConstant(color: Color) = memScoped {
        wgpuRenderPassEncoderSetBlendConstant(handler, map(color).ptr)
    }

    actual fun setStencilReference(reference: GPUStencilValue) {
        wgpuRenderPassEncoderSetStencilReference(handler, reference.toUInt())
    }

    actual fun beginOcclusionQuery(queryIndex: GPUSize32) {
        wgpuRenderPassEncoderBeginOcclusionQuery(handler, queryIndex.toUInt())
    }

    actual fun endOcclusionQuery() {
        wgpuRenderPassEncoderEndOcclusionQuery(handler)
    }

    private fun close() {
        wgpuRenderPassEncoderRelease(handler)
    }

}