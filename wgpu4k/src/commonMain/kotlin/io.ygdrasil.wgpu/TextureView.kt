package io.ygdrasil.wgpu

expect class TextureView : AutoCloseable {

    override fun close()
}

data class TextureViewDescriptor(
    val label: String? = null,
    val format: TextureFormat? = null,
    val dimension: TextureViewDimension? = null,
    val aspect: TextureAspect = TextureAspect.all,
    val baseMipLevel: GPUIntegerCoordinate = 0,
    val mipLevelCount: GPUIntegerCoordinate = 1,
    val baseArrayLayer: GPUIntegerCoordinate = 0,
    val arrayLayerCount: GPUIntegerCoordinate = 1,
)
