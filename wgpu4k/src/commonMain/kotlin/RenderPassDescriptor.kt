package io.ygdrasil.webgpu


// TODO to implement
class RenderPassTimestampWrites

data class RenderPassDescriptor(
    val colorAttachments: List<ColorAttachment> = listOf(),
    val depthStencilAttachment: DepthStencilAttachment? = null,
    val occlusionQuerySet: QuerySet? = null,
    val timestampWrites: RenderPassTimestampWrites? = null,
    val maxDrawCount: GPUSize64 = 50000000u,
    val label: String? = null,
) {

    data class DepthStencilAttachment(
        val view: TextureView,
        val depthClearValue: Float? = null,
        val depthLoadOp: LoadOp? = null,
        val depthStoreOp: StoreOp? = null,
        val depthReadOnly: Boolean = false,
        val stencilClearValue: GPUStencilValue = 0u,
        val stencilLoadOp: LoadOp? = null,
        val stencilStoreOp: StoreOp? = null,
        val stencilReadOnly: Boolean = false,
    )

    data class ColorAttachment(
        val view: TextureView,
        val loadOp: LoadOp,
        val storeOp: StoreOp,
        val depthSlice: GPUIntegerCoordinate? = null,
        val resolveTarget: TextureView? = null,
        val clearValue: Color = Color(.0, .0, .0, .0),
    )
}