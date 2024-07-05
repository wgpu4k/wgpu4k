

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.*
import io.ygdrasil.wgpu.internal.js.GPURenderPassTimestampWrites
import io.ygdrasil.wgpu.mapper.map

actual class CommandEncoder(private val handler: GPUCommandEncoder) : AutoCloseable {
	actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder {
		return RenderPassEncoder(handler.beginRenderPass(descriptor.convert()))
	}

	actual fun finish(): CommandBuffer {
		return CommandBuffer(handler.finish())
	}

	actual fun copyTextureToTexture(
		source: ImageCopyTexture,
		destination: ImageCopyTexture,
		copySize: Size3D
	) {
		handler.copyTextureToTexture(
			map(source),
			map(destination),
			copySize.toArray()
		)
	}

	actual fun copyTextureToBuffer(source: ImageCopyTexture, destination: ImageCopyBuffer, copySize: Size3D) {
		handler.copyTextureToBuffer(
			map(source),
			map(destination),
			copySize.toArray()
		)
	}

	actual fun copyBufferToTexture(source: ImageCopyBuffer, destination: ImageCopyTexture, copySize: Size3D) {
		handler.copyBufferToTexture(
			map(source),
			map(destination),
			copySize.toArray()
		)
	}

	actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder =
		descriptor?.convert()
			.let { handler.beginComputePass(it ?: undefined) }
			.let { ComputePassEncoder(it) }

	actual override fun close() {
		// Nothing to do
	}
}

private fun ComputePassDescriptor?.convert(): GPUComputePassDescriptor {
	TODO()
}


private fun RenderPassDescriptor.convert(): GPURenderPassDescriptor = object : GPURenderPassDescriptor {
	override var colorAttachments: Array<GPURenderPassColorAttachment> =
		this@convert.colorAttachments.map { it.convert() }.toTypedArray()
	override var label: String? = this@convert.label ?: undefined
	override var depthStencilAttachment: GPURenderPassDepthStencilAttachment? =
		this@convert.depthStencilAttachment?.convert() ?: undefined
	override var occlusionQuerySet: GPUQuerySet? = undefined // TODO map this
	override var timestampWrites: GPURenderPassTimestampWrites? = undefined // TODO map this
	override var maxDrawCount: GPUSize64? = this@convert.maxDrawCount
}

private fun RenderPassDescriptor.RenderPassDepthStencilAttachment.convert(): GPURenderPassDepthStencilAttachment =
	object : GPURenderPassDepthStencilAttachment {
		override var view: GPUTextureView = this@convert.view.handler
		override var depthClearValue: Number? = this@convert.depthClearValue ?: undefined
		override var depthLoadOp: String? = this@convert.depthLoadOp?.name ?: undefined
		override var depthStoreOp: String? = this@convert.depthStoreOp?.name ?: undefined
		override var depthReadOnly: Boolean? = this@convert.depthReadOnly
		override var stencilClearValue: GPUStencilValue? = this@convert.stencilClearValue
		override var stencilLoadOp: String? = this@convert.stencilLoadOp?.name ?: undefined
		override var stencilStoreOp: String? = this@convert.stencilStoreOp?.name ?: undefined
		override var stencilReadOnly: Boolean? = this@convert.stencilReadOnly
	}

private fun RenderPassDescriptor.ColorAttachment.convert(): GPURenderPassColorAttachment =
	object : GPURenderPassColorAttachment {
		override var view: GPUTextureView = this@convert.view.handler
		override var loadOp: String = this@convert.loadOp.name
		override var storeOp: String = this@convert.storeOp.name
		override var depthSlice: GPUIntegerCoordinate? = this@convert.depthSlice ?: undefined
		override var resolveTarget: GPUTextureView? = this@convert.resolveTarget?.handler ?: undefined
		override var clearValue: GPUColorDict? = map(this@convert.clearValue)
	}

private fun map(input: Array<Number>): GPUColorDict = object : GPUColorDict {
	override var r: Number = input[0]
	override var g: Number = input[1]
	override var b: Number = input[2]
	override var a: Number = input[3]
}
