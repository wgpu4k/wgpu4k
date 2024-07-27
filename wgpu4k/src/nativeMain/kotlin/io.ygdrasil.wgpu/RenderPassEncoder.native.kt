@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.toPointerArray
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import webgpu.*

actual class RenderPassEncoder(internal val handler: WGPURenderPassEncoder) : AutoCloseable {

    actual fun end() {
        wgpuRenderPassEncoderEnd(handler)
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        wgpuRenderPassEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    ) {
        wgpuRenderPassEncoderDraw(
            handler,
            vertexCount.toUInt(),
            instanceCount.toUInt(),
            firstVertex.toUInt(),
            firstInstance.toUInt()
        )
    }

    actual fun setBindGroup(index: Int, bindGroup: BindGroup) {
        wgpuRenderPassEncoderSetBindGroup(
            handler,
            index.toUInt(),
            bindGroup.handler,
            0uL,
            null
        )
    }

    actual fun setVertexBuffer(slot: Int, buffer: Buffer) {
        wgpuRenderPassEncoderSetVertexBuffer(
            handler,
            slot.toUInt(),
            buffer.handler,
            0uL,
            buffer.size.toULong()
        )
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        wgpuRenderPassEncoderSetIndexBuffer(
            handler,
            buffer.handler,
            indexFormat.value.toUInt(),
            offset.toULong(),
            size.toULong()
        )
    }

    actual fun executeBundles(bundles: List<RenderBundle>) = memScoped {
        wgpuRenderPassEncoderExecuteBundles(
            handler,
            bundles.size.toULong(),
            bundles.map { it.handler }.toPointerArray(this)
        )
    }

    actual override fun close() {
        wgpuRenderPassEncoderRelease(handler)
    }

}