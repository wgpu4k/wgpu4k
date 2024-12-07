package io.ygdrasil.wgpu

interface RenderingContext: AutoCloseable {
    val width: UInt
    val height: UInt
    val textureFormat: TextureFormat

    fun getCurrentTexture(): Texture
}