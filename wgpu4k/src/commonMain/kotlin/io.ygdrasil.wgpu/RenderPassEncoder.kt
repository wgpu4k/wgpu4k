package io.ygdrasil.wgpu

expect class RenderPassEncoder {

    fun end()

    fun setPipeline(renderPipeline: RenderPipeline)

    fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32 = 1,
        firstVertex: GPUSize32 = 0,
        firstInstance: GPUSize32 = 0,
    )

    fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32 = 1,
        firstIndex: GPUSize32 = 0,
        baseVertex: GPUSignedOffset32 = 0,
        firstInstance: GPUSize32 = 0,
    )

    fun drawIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64 = 0)
    fun drawIndexedIndirect(indirectBuffer: Buffer, indirectOffset: GPUSize64 = 0)

    fun setBindGroup(index: Int, bindGroup: BindGroup, dynamicOffsets:List<Int> = listOf())

    fun setVertexBuffer(slot: Int, buffer: Buffer)

    fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat, offset: GPUSize64 = 0, size: GPUSize64 = buffer.size)

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