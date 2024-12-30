package io.ygdrasil.webgpu

expect class TextureView : AutoCloseable {

    override fun close()
}

data class TextureViewDescriptor(
    val label: String? = null,
    val format: TextureFormat? = null,
    val dimension: TextureViewDimension? = null,
    val aspect: TextureAspect = TextureAspect.all,
    val baseMipLevel: GPUIntegerCoordinate = 0u,
    val mipLevelCount: GPUIntegerCoordinate = 1u,
    val baseArrayLayer: GPUIntegerCoordinate = 0u,
    val arrayLayerCount: GPUIntegerCoordinate = 1u,
)
