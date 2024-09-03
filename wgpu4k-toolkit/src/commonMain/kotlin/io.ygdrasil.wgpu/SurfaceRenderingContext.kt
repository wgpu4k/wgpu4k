package io.ygdrasil.wgpu

class SurfaceRenderingContext(
    private val surface: Surface
) : RenderingContext {

    override val width: Int
        get() = surface.width
    override val height: Int
        get() = surface.height
    override val textureFormat: TextureFormat
        get() = surface.preferredCanvasFormat
        ?: TextureFormat.rgba8unormsrgb?.takeIf { surface.supportedFormats.contains(it) }
        ?: TextureFormat.rgba8unorm?.takeIf { surface.supportedFormats.contains(it) }
        ?: surface.supportedFormats.first()

    override fun getCurrentTexture(): Texture {
        return surface.getCurrentTexture()
    }

    override fun close() {
        // Nothing to do here
    }
}