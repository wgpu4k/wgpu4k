package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*

actual class RenderPassEncoder(private val handler: WGPURenderPassEncoder) : AutoCloseable {
    actual fun end() {
        logUnitNative { "wgpuRenderPassEncoderEnd" to listOf() }
        wgpuRenderPassEncoderEnd(handler)
    }

    actual fun setPipeline(renderPipeline: RenderPipeline) {
        logUnitNative { "wgpuRenderPassEncoderSetPipeline" to listOf(renderPipeline.handler) }
        wgpuRenderPassEncoderSetPipeline(handler, renderPipeline.handler)
    }

    actual fun draw(
            vertexCount: GPUSize32,
            instanceCount: GPUSize32,
            firstVertex: GPUSize32,
            firstInstance: GPUSize32
    ) {
        logUnitNative { "wgpuRenderPassEncoderDraw" to listOf(vertexCount, instanceCount, firstVertex, firstInstance) }
        wgpuRenderPassEncoderDraw(handler, vertexCount, instanceCount, firstVertex, firstInstance)
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
                handler,
                index,
                bindGroup.handler,
                0L.toNativeLong(),
                null
        )
    }

    actual fun setVertexBuffer(slot: Int, buffer: Buffer) {
        logUnitNative {
            "wgpuRenderPassEncoderSetVertexBuffer" to listOf(slot,
                    buffer.handler,
                    0L,
                    buffer.size)
        }
        wgpuRenderPassEncoderSetVertexBuffer(
                handler,
                slot,
                buffer.handler,
                0L,
                buffer.size
        )
    }

    override fun close() {
        logUnitNative { "wgpuRenderPassEncoderRelease" to listOf() }
        wgpuRenderPassEncoderRelease(handler)
    }

}