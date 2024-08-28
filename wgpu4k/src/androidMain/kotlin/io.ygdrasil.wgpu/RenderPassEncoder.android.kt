package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class RenderPassEncoder(val handler: Long) : AutoCloseable {

    actual fun end() {
        JniInterface.wgpuRenderPassEncoderEnd(handler)
        close()
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        JniInterface.wgpuRenderPassEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    ) {
        JniInterface.wgpuRenderPassEncoderDraw(
            handler,
            vertexCount,
            instanceCount,
            firstVertex,
            firstInstance
        )
    }

    actual fun setBindGroup(index: Int, bindGroup: BindGroup) {
        JniInterface.wgpuRenderPassEncoderSetBindGroup(
            handler,
            index,
            bindGroup.handler,
            0L,
            null
        )
    }

    actual fun setVertexBuffer(slot: Int, buffer: Buffer) {
        JniInterface.wgpuRenderPassEncoderSetVertexBuffer(
            handler,
            slot,
            buffer.handler,
            0L,
            buffer.size
        )
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        JniInterface.wgpuRenderPassEncoderSetIndexBuffer(
            handler,
            buffer.handler,
            indexFormat.value,
            offset,
            size
        )
    }
    actual fun executeBundles(bundles: List<RenderBundle>) {
        JniInterface.wgpuRenderPassEncoderExecuteBundles(
            handler,
            bundles.size.toLong(),
            bundles
        )
    }

    actual override fun close() {
        JniInterface.wgpuRenderPassEncoderRelease(handler)
    }

}