package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPURenderPassEncoder
import io.ygdrasil.wgpu.internal.js.mapJsArray
import io.ygdrasil.wgpu.internal.js.toJsNumber

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

}