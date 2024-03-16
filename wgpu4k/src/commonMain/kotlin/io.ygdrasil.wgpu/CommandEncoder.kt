@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

expect class CommandEncoder : AutoCloseable {

	fun beginRenderPass(renderPassDescriptor: RenderPassDescriptor): RenderPassEncoder

	fun finish(): CommandBuffer

	fun copyTextureToTexture(
		source: ImageCopyTexture,
		destination: ImageCopyTexture,
		copySize: GPUIntegerCoordinates
	)
}

data class ImageCopyTexture(
	var texture: Texture,
	var mipLevel: GPUIntegerCoordinate? = null,
	var origin: GPUIntegerCoordinates? = null,
	var aspect: TextureAspect? = null,
)