package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*

actual class RenderPassEncoder(private val handler: WGPURenderPassEncoder) : AutoCloseable {
	actual fun end() {
		wgpuRenderPassEncoderEnd(handler)
	}

	actual fun setPipeline(renderPipeline: RenderPipeline) {
		wgpuRenderPassEncoderSetPipeline(handler, renderPipeline.handler)
	}

	actual fun draw(
		vertexCount: GPUSize32,
		instanceCount: GPUSize32?,
		firstVertex: GPUSize32?,
		firstInstance: GPUSize32?
	) {
		wgpuRenderPassEncoderDraw(handler, vertexCount, instanceCount ?: 1, firstVertex ?: 0, firstInstance ?: 0)
	}

	actual fun setBindGroup(index: Int, bindGroup: BindGroup) {
		wgpuRenderPassEncoderSetBindGroup(
			handler,
			index,
			bindGroup.handler,
			0L.toNativeLong(),
			null
		)
	}

	actual fun setVertexBuffer(slot: Int, buffer: Buffer) {
		wgpuRenderPassEncoderSetVertexBuffer(
			handler,
			slot,
			buffer.handler,
			0L,
			buffer.size
		)
	}

	override fun close() {
		wgpuRenderPassEncoderRelease(handler)
	}

}