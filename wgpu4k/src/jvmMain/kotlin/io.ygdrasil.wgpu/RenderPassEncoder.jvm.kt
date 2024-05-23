package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.internal.jvm.panama.webgpu_h
import java.lang.foreign.MemorySegment

actual class RenderPassEncoder(private val handler: MemorySegment) : AutoCloseable {

    val handler2: WGPURenderPassEncoder = WGPURenderPassEncoderImpl(handler.toPointer())
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
        logUnitNative { "wgpuRenderPassEncoderDraw" to listOf(vertexCount, instanceCount, firstVertex, firstInstance) }
        wgpuRenderPassEncoderDraw(handler2, vertexCount, instanceCount, firstVertex, firstInstance)
    }

    actual fun setBindGroup(index: Int, bindGroup: BindGroup) {
        logUnitNative {
            "wgpuRenderPassEncoderSetBindGroup" to listOf(
                index,
                bindGroup.handler,
                0L.toNativeLong(),
                null
            )
        }
        wgpuRenderPassEncoderSetBindGroup(
                handler2,
                index,
                WGPUBindGroupImpl(bindGroup.handler.toPointer()),
                0L.toNativeLong(),
                null
        )
    }

    actual fun setVertexBuffer(slot: Int, buffer: Buffer) {
        logUnitNative {
            "wgpuRenderPassEncoderSetVertexBuffer" to listOf(slot,
                    buffer.handler2,
                    0L,
                    buffer.size)
        }
        wgpuRenderPassEncoderSetVertexBuffer(
                handler2,
                slot,
                buffer.handler2,
                0L,
                buffer.size
        )
    }

    actual override fun close() {
        webgpu_h.wgpuRenderPassEncoderRelease(handler)
    }

}