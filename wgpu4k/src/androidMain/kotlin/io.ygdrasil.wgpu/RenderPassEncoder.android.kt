package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.internal.toLNativeArray
import io.ygdrasil.wgpu.mapper.map
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

    actual fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32,
    ) {
        NativeWgpu4k.wgpuRenderPassEncoderDrawIndexed(handler, indexCount, instanceCount, firstIndex, baseVertex, firstInstance)
    }

    actual fun drawIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64){
        NativeWgpu4k.wgpuRenderPassEncoderDrawIndexedIndirect(handler, indirectBuffer.handler, indirectOffset)
    }

    actual fun drawIndexedIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64) {
        NativeWgpu4k.wgpuRenderPassEncoderDrawIndexedIndirect(handler, indirectBuffer.handler, indirectOffset)
    }

    actual fun setBindGroup(index: Int, bindGroup: BindGroup, dynamicOffsets: List<Int>) = scoped { arena ->
        NativeWgpu4k.wgpuRenderPassEncoderSetBindGroup(
            handler,
            index,
            bindGroup.handler,
            dynamicOffsets.size.toLong(),
            arena.map(dynamicOffsets)
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
            bundles.map { it.handler }.toLNativeArray(arena.arena).toAddress()
        )
    }

    actual fun setViewport(x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float) {
        NativeWgpu4k.wgpuRenderPassEncoderSetViewport(
            handler,
            x, y, width, height, minDepth, maxDepth
        )
    }

    actual fun setScissorRect(
        x: GPUIntegerCoordinate,
        y: GPUIntegerCoordinate,
        width: GPUIntegerCoordinate,
        height: GPUIntegerCoordinate,
    ) {
        NativeWgpu4k.wgpuRenderPassEncoderSetScissorRect(handler, x, y, width, height)
    }

    actual fun setBlendConstant(color: Color) = scoped { arena ->
        NativeWgpu4k.wgpuRenderPassEncoderSetBlendConstant(handler, arena.map(color).pointer.toAddress())
    }

    actual fun setStencilReference(reference: GPUStencilValue) {
        NativeWgpu4k.wgpuRenderPassEncoderSetStencilReference(handler, reference)
    }

    actual fun beginOcclusionQuery(queryIndex: GPUSize32) {
        NativeWgpu4k.wgpuRenderPassEncoderBeginOcclusionQuery(handler, queryIndex)
    }

    actual fun endOcclusionQuery() {
        NativeWgpu4k.wgpuRenderPassEncoderEndOcclusionQuery(handler)
    }

    private fun close() {
        NativeWgpu4k.wgpuRenderPassEncoderRelease(handler)
    }

}