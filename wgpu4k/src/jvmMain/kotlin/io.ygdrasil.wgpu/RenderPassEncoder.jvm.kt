package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.panama.webgpu_h
import java.lang.foreign.MemorySegment

actual class RenderPassEncoder(private val handler: MemorySegment) : AutoCloseable {

    actual fun end() {
        webgpu_h.wgpuRenderPassEncoderEnd(handler)
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        webgpu_h.wgpuRenderPassEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    ) {
        webgpu_h.wgpuRenderPassEncoderDraw(handler, vertexCount, instanceCount, firstVertex, firstInstance)
    }

    actual fun setBindGroup(index: Int, bindGroup: BindGroup) {
        webgpu_h.wgpuRenderPassEncoderSetBindGroup(
            handler,
            index,
            bindGroup.handler,
            0L,
            MemorySegment.NULL
        )
    }

    actual fun setVertexBuffer(slot: Int, buffer: Buffer) {
        webgpu_h.wgpuRenderPassEncoderSetVertexBuffer(
            handler,
            slot,
            buffer.handler,
            0L,
            buffer.size
        )
    }

    actual override fun close() {
        webgpu_h.wgpuRenderPassEncoderRelease(handler)
    }

}