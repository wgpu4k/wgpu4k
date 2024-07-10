package io.ygdrasil.wgpu


// TODO to implement
class RenderPassTimestampWrites

data class RenderPassDescriptor(
	val colorAttachments: Array<ColorAttachment> = arrayOf(),
	val depthStencilAttachment: RenderPassDepthStencilAttachment? = null,
	val occlusionQuerySet: QuerySet? = null,
	val timestampWrites: RenderPassTimestampWrites? = null,
	val maxDrawCount: GPUSize64 = 50000000,
	val label: String? = null
) {

	data class RenderPassDepthStencilAttachment(
		val view: TextureView,
		val depthClearValue: Float? = null,
		val depthLoadOp: LoadOp? = null,
		val depthStoreOp: StoreOp? = null,
		val depthReadOnly: Boolean = false,
		val stencilClearValue: GPUStencilValue = 0,
		val stencilLoadOp: LoadOp? = null,
		val stencilStoreOp: StoreOp? = null,
		val stencilReadOnly: Boolean = false
	)

	data class ColorAttachment(
		val view: TextureView,
		val loadOp: LoadOp,
		val storeOp: StoreOp,
		val depthSlice: GPUIntegerCoordinate? = null,
		val resolveTarget: TextureView? = null,
		val clearValue: Array<Number> = arrayOf(0, 0, 0, 0),
	)
}