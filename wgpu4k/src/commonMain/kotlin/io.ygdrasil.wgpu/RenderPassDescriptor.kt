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
	var maxDrawCount: GPUSize64? = null,
	var label: String? = null
) {

	data class RenderPassDepthStencilAttachment(
		var view: TextureView,
		var depthClearValue: Float? = null,
		var depthLoadOp: LoadOp? = null, /* "load" | "clear" */
		var depthStoreOp: StoreOp? = null, /* "store" | "discard" */
		var depthReadOnly: Boolean? = null,
		var stencilClearValue: GPUStencilValue? = null,
		var stencilLoadOp: LoadOp? = null, /* "load" | "clear" */
		var stencilStoreOp: StoreOp? = null, /* "store" | "discard" */
		var stencilReadOnly: Boolean? = null
	)

	data class ColorAttachment(
		var view: TextureView,
		var loadOp: String, /* "load" | "clear" */
		var storeOp: String, /* "store" | "discard" */
		var depthSlice: GPUIntegerCoordinate? = null,
		var resolveTarget: TextureView? = null,
		var clearValue: Array<Number>? = null,
	)
}