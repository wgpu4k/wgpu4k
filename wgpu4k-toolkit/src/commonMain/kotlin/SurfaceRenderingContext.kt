package io.ygdrasil.webgpu

class SurfaceRenderingContext(
    private val surface: Surface,
    override val textureFormat: GPUTextureFormat
) : RenderingContext {

    override val width: UInt
        get() = surface.width
    override val height: UInt
        get() = surface.height

    override fun getCurrentTexture(): GPUTexture {
        return surface.getCurrentTexture().texture
    }

    override fun close() {
        // Nothing to do here
    }
}