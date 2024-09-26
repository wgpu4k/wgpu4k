package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPURenderPassEncoder
import io.ygdrasil.wgpu.internal.js.mapJsArray
import io.ygdrasil.wgpu.internal.js.toJsNumber
import io.ygdrasil.wgpu.mapper.map

actual class RenderPassEncoder(internal val handler: GPURenderPassEncoder) {

    actual fun end() {
        handler.end()
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        handler.setPipeline(renderPipeline.handler)
    }

    actual fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32,
    ) {
        handler.draw(
            vertexCount,
            instanceCount,
            firstVertex,
            firstInstance
        )
    }

    actual fun setBindGroup(index: Int, bindGroup: BindGroup) {
        handler.setBindGroup(index, bindGroup.handler)
    }

    actual fun setVertexBuffer(slot: Int, buffer: Buffer) {
        handler.setVertexBuffer(slot, buffer.handler)
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        handler.setIndexBuffer(buffer.handler, indexFormat.name, offset.toJsNumber(), size.toJsNumber())
    }

    actual fun executeBundles(bundles: List<RenderBundle>) {
        handler.executeBundles(bundles.mapJsArray { it.handler })
    }

    actual fun setViewport(x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float) {
        handler.setViewport(
            x.toJsNumber(), y.toJsNumber(), width.toJsNumber(), height.toJsNumber(), minDepth.toJsNumber(), maxDepth.toJsNumber()
        )
    }

    actual fun setScissorRect(
        x: GPUIntegerCoordinate,
        y: GPUIntegerCoordinate,
        width: GPUIntegerCoordinate,
        height: GPUIntegerCoordinate,
    ) {
        handler.setScissorRect(x.toJsNumber(), y.toJsNumber(), width.toJsNumber(), height.toJsNumber())
    }

    actual fun setBlendConstant(color: Color) {
        handler.setBlendConstant(map(color))
    }

    actual fun setStencilReference(reference: GPUStencilValue) {
        handler.setStencilReference(reference.toJsNumber())
    }

    actual fun beginOcclusionQuery(queryIndex: GPUSize32) {
        handler.beginOcclusionQuery(queryIndex)
    }

    actual fun endOcclusionQuery() {
        handler.endOcclusionQuery()
    }

}