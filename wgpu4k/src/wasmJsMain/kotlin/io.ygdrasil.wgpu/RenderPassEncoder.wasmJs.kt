package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPURenderPassEncoder

actual class RenderPassEncoder(internal val handler: GPURenderPassEncoder): AutoCloseable {

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
        firstInstance: GPUSize32
    ) {
        handler.draw(
            vertexCount,
            instanceCount,
            firstVertex,
            firstInstance
        )
    }

    actual fun setBindGroup(index: Int, bindGroup: BindGroup) {
        TODO("Not yet implemented")
    }

    actual fun setVertexBuffer(slot: Int, buffer: Buffer) {
        TODO("Not yet implemented")
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        TODO("Not yet implemented")
    }

    actual fun executeBundles(bundles: Array<RenderBundle>) {
        TODO("Not yet implemented")
    }

    actual override fun close() {
        TODO("Not yet implemented")
    }

}