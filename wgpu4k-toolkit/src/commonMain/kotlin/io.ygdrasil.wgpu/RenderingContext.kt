package io.ygdrasil.wgpu

interface RenderingContext: AutoCloseable {
    val width: Int
    val height: Int
    val textureFormat: TextureFormat

    fun getCurrentTexture(): Texture
}