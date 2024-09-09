package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.mapper.map
import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k

actual class RenderBundleEncoder(val handler: Long) : AutoCloseable {

    actual fun finish(descriptor: RenderBundleDescriptor): RenderBundle = scoped { arena ->
        arena.map(descriptor)
            .let { NativeWgpu4k.wgpuRenderBundleEncoderFinish(handler, it) }
            .let(::RenderBundle)
    }

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup) {
        NativeWgpu4k.wgpuRenderBundleEncoderSetBindGroup(handler, index, bindGroup.handler, 0, 0L)
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        NativeWgpu4k.wgpuRenderBundleEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun setVertexBuffer(slot: GPUIndex32, buffer: Buffer, offset: GPUSize64, size: GPUSize64) {
        NativeWgpu4k.wgpuRenderBundleEncoderSetVertexBuffer(handler, slot, buffer.handler, offset, size)
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        NativeWgpu4k.wgpuRenderBundleEncoderSetIndexBuffer(handler, buffer.handler, indexFormat.value, offset, size)
    }

    actual fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32
    ) {
        NativeWgpu4k.wgpuRenderBundleEncoderDrawIndexed(
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
        NativeWgpu4k.wgpuRenderBundleEncoderDraw(handler, vertexCount, instanceCount, firstVertex, firstInstance)
    }

    actual override fun close() {
        NativeWgpu4k.wgpuRenderBundleEncoderRelease(handler)
    }
}
