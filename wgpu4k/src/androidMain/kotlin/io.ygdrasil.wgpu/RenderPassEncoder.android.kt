package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class RenderPassEncoder(val handler: Long) : AutoCloseable {

    actual fun end() {
        JniInterfaceV2.wgpuRenderPassEncoderEnd(handler)
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        JniInterfaceV2.wgpuRenderPassEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    ) {
        JniInterfaceV2.wgpuRenderPassEncoderDraw(
            handler,
            vertexCount,
            instanceCount,
            firstVertex,
            firstInstance
        )
    }

    actual fun setBindGroup(index: Int, bindGroup: BindGroup) {
        JniInterfaceV2.wgpuRenderPassEncoderSetBindGroup(
            handler,
            index,
            bindGroup.handler,
            0L,
            null
        )
    }

    actual fun setVertexBuffer(slot: Int, buffer: Buffer) {
        JniInterfaceV2.wgpuRenderPassEncoderSetVertexBuffer(
            handler,
            slot,
            buffer.handler,
            0L,
            buffer.size
        )
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        JniInterfaceV2.wgpuRenderPassEncoderSetIndexBuffer(
            handler,
            buffer.handler,
            indexFormat.value,
            offset,
            size
        )
    }
    actual fun executeBundles(bundles: List<RenderBundle>) {
        JniInterfaceV2.wgpuRenderPassEncoderExecuteBundles(
            handler,
            bundles.size.toLong(),
            bundles
        )
    }

    actual override fun close() {
        JniInterfaceV2.wgpuRenderPassEncoderRelease(handler)
    }

}