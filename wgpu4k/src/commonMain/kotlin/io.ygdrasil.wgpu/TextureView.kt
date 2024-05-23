

package io.ygdrasil.wgpu

expect class TextureView: AutoCloseable {

	override fun close()
}

data class TextureViewDescriptor(
	var label: String? = null,
	var format: TextureFormat? = null,
	var dimension: TextureViewDimension? = null,
	var aspect: TextureAspect = TextureAspect.all,

	var baseMipLevel: GPUIntegerCoordinate = 0,

	var mipLevelCount: GPUIntegerCoordinate = 1,

	var baseArrayLayer: GPUIntegerCoordinate = 0,

	var arrayLayerCount: GPUIntegerCoordinate = 1,
)
