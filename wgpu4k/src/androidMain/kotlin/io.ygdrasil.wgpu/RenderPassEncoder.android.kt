package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.internal.toNativeArray
import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k

actual class RenderPassEncoder(val handler: Long) {

    actual fun end() {
        NativeWgpu4k.wgpuRenderPassEncoderEnd(handler)
        close()
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        NativeWgpu4k.wgpuRenderPassEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    ) {
        NativeWgpu4k.wgpuRenderPassEncoderDraw(handler, vertexCount, instanceCount, firstVertex, firstInstance)
    }

    actual fun setBindGroup(index: Int, bindGroup: BindGroup) {
        NativeWgpu4k.wgpuRenderPassEncoderSetBindGroup(
            handler,
            index,
            bindGroup.handler,
            0L,
            0L
        )
    }

    actual fun setVertexBuffer(slot: Int, buffer: Buffer) {
        NativeWgpu4k.wgpuRenderPassEncoderSetVertexBuffer(
            handler,
            slot,
            buffer.handler,
            0L,
            buffer.size
        )
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        NativeWgpu4k.wgpuRenderPassEncoderSetIndexBuffer(handler, buffer.handler, indexFormat.value, offset, size)
    }

    actual fun executeBundles(bundles: List<RenderBundle>) = scoped { arena ->
        NativeWgpu4k.wgpuRenderPassEncoderExecuteBundles(
            handler,
            bundles.size.toLong(),
            bundles.map { it.handler }.toNativeArray(arena.arena).toAddress()
        )
    }

    private fun close() {
        NativeWgpu4k.wgpuRenderPassEncoderRelease(handler)
    }

}