

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

}