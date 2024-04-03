package io.ygdrasil.wgpu


// TODO to implement
class GPUQuerySet

// TODO to implement
class GPURenderPassTimestampWrites

data class RenderPassDescriptor(
	var colorAttachments: Array<ColorAttachment> = arrayOf(),
	var depthStencilAttachment: RenderPassDepthStencilAttachment? = null,
	var occlusionQuerySet: GPUQuerySet? = null,
	var timestampWrites: GPURenderPassTimestampWrites? = null,
	var maxDrawCount: GPUSize64 = 50000000,
	var label: String? = null
) {

	data class RenderPassDepthStencilAttachment(
		var view: TextureView,
		var depthClearValue: Float? = null,
		var depthLoadOp: LoadOp? = null,
		var depthStoreOp: StoreOp? = null,
		var depthReadOnly: Boolean = false,
		var stencilClearValue: GPUStencilValue = 0,
		var stencilLoadOp: LoadOp? = null,
		var stencilStoreOp: StoreOp? = null,
		var stencilReadOnly: Boolean = false
	)

	data class ColorAttachment(
		var view: TextureView,
		var loadOp: LoadOp,
		var storeOp: StoreOp,
		var depthSlice: GPUIntegerCoordinate? = null,
		var resolveTarget: TextureView? = null,
		var clearValue: Array<Number> = arrayOf(0, 0, 0, 0),
	)
}