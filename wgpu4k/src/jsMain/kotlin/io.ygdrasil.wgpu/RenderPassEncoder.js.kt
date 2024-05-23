

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPURenderPassEncoder

actual class RenderPassEncoder(private val handler: GPURenderPassEncoder) : AutoCloseable {

	actual fun end() {
		handler.end()
	}

	actual fun setPipeline(renderPipeline: RenderPipeline) {
		handler.setPipeline(renderPipeline.handler)
	}

	actual fun draw(
		vertexCount: GPUSize32,
		instanceCount: GPUSize32,
		firstVertex: GPUSize32,
		firstInstance: GPUSize32
	) {
		handler.draw(
			vertexCount,
			instanceCount,
			firstVertex,
			firstInstance
		)
	}

	actual fun setBindGroup(index: Int, bindGroup: BindGroup) {
		handler.setBindGroup(index, bindGroup.handler)
	}

	actual fun setVertexBuffer(slot: Int, buffer: Buffer) {
		handler.setVertexBuffer(slot, buffer.handler)
	}

	override fun close() {
		// Nothing to do
	}
}