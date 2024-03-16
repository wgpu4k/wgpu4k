@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

expect class RenderPassEncoder: AutoCloseable {

	fun end()

	fun setPipeline(renderPipeline: RenderPipeline)

	fun draw(
		vertexCount: GPUSize32,
		instanceCount: GPUSize32? = null,
		firstVertex: GPUSize32? = null,
		firstInstance: GPUSize32? = null
	)

	fun setBindGroup(index: Int, bindGroup: BindGroup)

	fun setVertexBuffer(slot: Int, buffer: Buffer)

}