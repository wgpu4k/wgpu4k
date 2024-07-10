

package io.ygdrasil.wgpu

expect class RenderPassEncoder: AutoCloseable {

	fun end()

	fun setPipeline(renderPipeline: RenderPipeline)

	fun draw(
		vertexCount: GPUSize32,
		instanceCount: GPUSize32 = 1,
		firstVertex: GPUSize32 = 0,
		firstInstance: GPUSize32 = 0
	)

	fun setBindGroup(index: Int, bindGroup: BindGroup)

	fun setVertexBuffer(slot: Int, buffer: Buffer)

	fun setIndexBuffer(buffer: Buffer, indexFormat: IndexFormat , offset: GPUSize64 = 0, size: GPUSize64 = buffer.size)

	fun executeBundles(bundles: List<RenderBundle>)
	/* TODO
	fun setViewport(x: Number, y: Number, width: Number, height: Number, minDepth: Number, maxDepth: Number): Nothing?
    fun setScissorRect(x: GPUIntegerCoordinate, y: GPUIntegerCoordinate, width: GPUIntegerCoordinate, height: GPUIntegerCoordinate): Nothing?
    fun setBlendConstant(color: Array<Number>): Nothing?
    fun setBlendConstant(color: GPUColorDict): Nothing?
    fun setStencilReference(reference: GPUStencilValue): Nothing?
    fun beginOcclusionQuery(queryIndex: GPUSize32): Nothing?
    fun endOcclusionQuery(): Nothing?
    fun end(): Nothing?
	 */

	override fun close()

}