package io.ygdrasil.webgpu

interface RenderingContext: AutoCloseable {
    val width: UInt
    val height: UInt
    val textureFormat: GPUTextureFormat

    fun getCurrentTexture(): GPUTexture
}