package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.internal.toNativeArray

actual class RenderPassEncoder(val handler: Long) {

    actual fun end() {
        JnaInterface.wgpuRenderPassEncoderEnd(handler)
        close()
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        JnaInterface.wgpuRenderPassEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    ) {
        JnaInterface.wgpuRenderPassEncoderDraw(handler, vertexCount, instanceCount, firstVertex, firstInstance)
    }

    actual fun setBindGroup(index: Int, bindGroup: BindGroup) {
        JnaInterface.wgpuRenderPassEncoderSetBindGroup(
            handler,
            index,
            bindGroup.handler,
            0L,
            0L
        )
    }

    actual fun setVertexBuffer(slot: Int, buffer: Buffer) {
        JnaInterface.wgpuRenderPassEncoderSetVertexBuffer(
            handler,
            slot,
            buffer.handler,
            0L,
            buffer.size
        )
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        JnaInterface.wgpuRenderPassEncoderSetIndexBuffer(handler, buffer.handler, indexFormat.value, offset, size)
    }

    actual fun executeBundles(bundles: List<RenderBundle>) = scoped { arena ->
        JnaInterface.wgpuRenderPassEncoderExecuteBundles(
            handler,
            bundles.size.toLong(),
            bundles.map { it.handler }.toNativeArray(arena.arena).toAddress()
        )
    }

    private fun close() {
        JnaInterface.wgpuRenderPassEncoderRelease(handler)
    }

}