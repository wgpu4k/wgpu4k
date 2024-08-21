package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class RenderBundleEncoder(val handler: Long) : AutoCloseable {

    actual fun finish(descriptor: RenderBundleDescriptor): RenderBundle {
        return JniInterface.wgpuRenderBundleEncoderFinish(handler, descriptor)
            .let(::RenderBundle)
    }

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup) {
        JniInterface.wgpuRenderBundleEncoderSetBindGroup(
            handler,
            index,
            bindGroup.handler,
            0,
            null
        )
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        JniInterface.wgpuRenderBundleEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun setVertexBuffer(slot: GPUIndex32, buffer: Buffer, offset: GPUSize64, size: GPUSize64) {
        JniInterface.wgpuRenderBundleEncoderSetVertexBuffer(
            handler,
            slot,
            buffer.handler,
            offset,
            size
        )
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        JniInterface.wgpuRenderBundleEncoderSetIndexBuffer(
            handler,
            buffer.handler,
            indexFormat.value,
            offset,
            size
        )
    }

    actual fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32
    ) {
        JniInterface.wgpuRenderBundleEncoderDrawIndexed(
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
        JniInterface.wgpuRenderBundleEncoderDraw(
            handler,
            vertexCount,
            instanceCount,
            firstVertex,
            firstInstance
        )
    }

    actual override fun close() {
        JniInterface.wgpuRenderBundleEncoderRelease(handler)
    }
}
