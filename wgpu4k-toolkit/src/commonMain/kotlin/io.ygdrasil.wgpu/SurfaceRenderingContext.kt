package io.ygdrasil.wgpu

class SurfaceRenderingContext(
    private val surface: Surface
) : RenderingContext {

    override val width: Int
        get() = surface.width
    override val height: Int
        get() = surface.height
    override val textureFormat: TextureFormat
        get() = surface.preferredCanvasFormat ?: error("remove this field")

    override fun getCurrentTexture(): Texture {
        return surface.getCurrentTexture()
    }

    override fun close() {
        // Nothing to do here
    }
}