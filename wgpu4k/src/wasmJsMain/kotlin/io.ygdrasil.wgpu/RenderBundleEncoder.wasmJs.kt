package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPURenderBundleEncoder
import io.ygdrasil.webgpu.internal.js.toJsNumber
import io.ygdrasil.webgpu.mapper.map

actual class RenderBundleEncoder(internal val handler: GPURenderBundleEncoder) : AutoCloseable {

    actual fun finish(descriptor: RenderBundleDescriptor): RenderBundle =
        map(descriptor)
            .let { handler.finish(it) }
            .let(::RenderBundle)

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup) {
        handler.setBindGroup(index, bindGroup.handler)
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        handler.setPipeline(renderPipeline.handler)
    }

    actual fun setVertexBuffer(slot: GPUIndex32, buffer: Buffer, offset: GPUSize64, size: GPUSize64) {
        handler.setVertexBuffer(slot, buffer.handler, offset.toJsNumber(), size.toJsNumber())
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        handler.setIndexBuffer(buffer.handler, indexFormat.name, offset.toJsNumber(), size.toJsNumber())
    }

    actual fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32,
    ) {
        handler.drawIndexed(indexCount, instanceCount, firstIndex, baseVertex, firstInstance)
    }

    actual fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32,
    ) {
        handler.draw(vertexCount, instanceCount, firstVertex, firstInstance)
    }

    actual override fun close() {
        // Nothing to do on JS
    }
}
