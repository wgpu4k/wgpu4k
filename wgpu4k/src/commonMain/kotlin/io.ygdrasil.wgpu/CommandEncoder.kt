@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

expect class CommandEncoder : AutoCloseable {

	fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder

	fun finish(): CommandBuffer

	fun copyTextureToTexture(
		source: ImageCopyTexture,
		destination: ImageCopyTexture,
		copySize: GPUIntegerCoordinates
	)
}

data class ImageCopyTexture(
	var texture: Texture,
	var mipLevel: GPUIntegerCoordinate = 0,
	var origin: GPUIntegerCoordinates = 0 to 0,
	var aspect: TextureAspect = TextureAspect.all,
)