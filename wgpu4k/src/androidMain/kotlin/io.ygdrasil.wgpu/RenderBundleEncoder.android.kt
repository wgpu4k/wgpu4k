package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class RenderBundleEncoder(val handler: Long) : AutoCloseable {

    actual fun finish(descriptor: RenderBundleDescriptor): RenderBundle {
        return JniInterfaceV2.wgpuRenderBundleEncoderFinish(handler, descriptor)
            .let(::RenderBundle)
    }

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup) {
        JniInterfaceV2.wgpuRenderBundleEncoderSetBindGroup(
            handler,
            index,
            bindGroup.handler,
            0,
            null
        )
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        JniInterfaceV2.wgpuRenderBundleEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun setVertexBuffer(slot: GPUIndex32, buffer: Buffer, offset: GPUSize64, size: GPUSize64) {
        JniInterfaceV2.wgpuRenderBundleEncoderSetVertexBuffer(
            handler,
            slot,
            buffer.handler,
            offset,
            size
        )
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        JniInterfaceV2.wgpuRenderBundleEncoderSetIndexBuffer(
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
        JniInterfaceV2.wgpuRenderBundleEncoderDrawIndexed(
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
        JniInterfaceV2.wgpuRenderBundleEncoderDraw(
            handler,
            vertexCount,
            instanceCount,
            firstVertex,
            firstInstance
        )
    }

    actual override fun close() {
        JniInterfaceV2.wgpuRenderBundleEncoderRelease(handler)
    }
}
