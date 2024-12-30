package io.ygdrasil.wgpu

expect class RenderPassEncoder {

    fun end()

    fun setPipeline(renderPipeline: RenderPipeline)

    fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32 = 1u,
        firstVertex: GPUSize32 = 0u,
        firstInstance: GPUSize32 = 0u,
    )

    fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32 = 1u,
        firstIndex: GPUSize32 = 0u,
        baseVertex: GPUSignedOffset32 = 0,
        firstInstance: GPUSize32 = 0u,
    )

    fun drawIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64 = 0u)
    fun drawIndexedIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64 = 0u)

    fun setBindGroup(index: GPUIndex32, bindGroup: BindGroup, dynamicOffsets: List<GPUIndex32> = listOf())

    fun setVertexBuffer(slot: GPUIndex32, buffer: Buffer)

    fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64 = 0u, size: GPUSize64 = buffer.size)

    fun executeBundles(bundles: List<RenderBundle>)

    fun setViewport(x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float)

    fun setScissorRect(
        x: GPUIntegerCoordinate,
        y: GPUIntegerCoordinate,
        width: GPUIntegerCoordinate,
        height: GPUIntegerCoordinate,
    )

    fun setBlendConstant(color: Color)

    fun setStencilReference(reference: GPUStencilValue)

    fun beginOcclusionQuery(queryIndex: GPUSize32)

    fun endOcclusionQuery()
}