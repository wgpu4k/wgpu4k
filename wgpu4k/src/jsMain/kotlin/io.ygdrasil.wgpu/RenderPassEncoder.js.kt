

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

	actual fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64, size: GPUSize64) {
		handler.setIndexBuffer(buffer.handler, indexFormat.name, offset, size)
	}

	actual fun executeBundles(bundles: Array<RenderBundle>) {
		handler.executeBundles(bundles.map { it.handler }.toTypedArray())
	}

	actual override fun close() {
		// Nothing to do
	}


}