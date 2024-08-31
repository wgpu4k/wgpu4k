package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.mapper.map

actual class RenderBundleEncoder(val handler: Long) : AutoCloseable {

    actual fun finish(descriptor: RenderBundleDescriptor): RenderBundle = scoped { arena ->
        arena.map(descriptor)
            .let { JnaInterface.wgpuRenderBundleEncoderFinish(handler, it) }
            .let(::RenderBundle)
    }

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup) {
        JnaInterface.wgpuRenderBundleEncoderSetBindGroup(handler, index, bindGroup.handler, 0, 0L)
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        JnaInterface.wgpuRenderBundleEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun setVertexBuffer(slot: GPUIndex32, buffer: Buffer, offset: GPUSize64, size: GPUSize64) {
        JnaInterface.wgpuRenderBundleEncoderSetVertexBuffer(handler, slot, buffer.handler, offset, size)
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        JnaInterface.wgpuRenderBundleEncoderSetIndexBuffer(handler, buffer.handler, indexFormat.value, offset, size)
    }

    actual fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32
    ) {
        JnaInterface.wgpuRenderBundleEncoderDrawIndexed(
            handler,
            indexCount,
            instanceCount,
            firstIndex,
            baseVertex,
            firstInstance
        )
    }

    actual fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    ) {
        JnaInterface.wgpuRenderBundleEncoderDraw(handler, vertexCount, instanceCount, firstVertex, firstInstance)
    }

    actual override fun close() {
        JnaInterface.wgpuRenderBundleEncoderRelease(handler)
    }
}
