@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.mapper.map
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import webgpu.native.*

actual class RenderBundleEncoder(internal val handler: WGPURenderBundleEncoder) : AutoCloseable {

    actual fun finish(descriptor: RenderBundleDescriptor): RenderBundle = memScoped {
        map(descriptor)
            .let { wgpuRenderBundleEncoderFinish(handler, it.ptr) }
            ?.let(::RenderBundle) ?: error("Unable to get render bundle from descriptor $descriptor")
    }

    actual fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup) {
        wgpuRenderBundleEncoderSetBindGroup(handler, index.toUInt(), bindGroup.handler, 0u, null)
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        wgpuRenderBundleEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun setVertexBuffer(slot: GPUIndex32, buffer: Buffer, offset: GPUSize64, size: GPUSize64) {
        wgpuRenderBundleEncoderSetVertexBuffer(handler, slot.toUInt(), buffer.handler, offset.toULong(), size.toULong())
    }

    actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
        wgpuRenderBundleEncoderSetIndexBuffer(
            handler,
            buffer.handler,
            indexFormat.value.toUInt(),
            offset.toULong(),
            size.toULong()
        )
    }

    actual fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32
    ) {
        wgpuRenderBundleEncoderDrawIndexed(
            handler,
            indexCount.toUInt(),
            instanceCount.toUInt(),
            firstIndex.toUInt(),
            baseVertex,
            firstInstance.toUInt()
        )
    }

    actual fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    ) {
        wgpuRenderBundleEncoderDraw(
            handler,
            vertexCount.toUInt(),
            instanceCount.toUInt(),
            firstVertex.toUInt(),
            firstInstance.toUInt()
        )
    }

    actual override fun close() {
        wgpuRenderBundleEncoderRelease(handler)
    }
}
