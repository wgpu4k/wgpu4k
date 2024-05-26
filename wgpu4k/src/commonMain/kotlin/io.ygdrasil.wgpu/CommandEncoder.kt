

package io.ygdrasil.wgpu

expect class CommandEncoder : AutoCloseable {

	fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder

	fun finish(): CommandBuffer

	fun copyTextureToTexture(
		source: ImageCopyTexture,
		destination: ImageCopyTexture,
		copySize: GPUIntegerCoordinates
	)

	fun beginComputePass(descriptor: ComputePassDescriptor? = null): ComputePassEncoder

	override fun close()
}

data class ImageCopyTexture(
	var texture: Texture,
	var mipLevel: GPUIntegerCoordinate = 0,
	var origin: Origin3D = Origin3D(0, 0),
	var aspect: TextureAspect = TextureAspect.all,
)