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
        /*
            https://developer.mozilla.org/en-US/docs/Web/API/GPU/getPreferredCanvasFormat
            rgba8unorm or bgra8unorm are default format supported on web
         */
        ?: TextureFormat.rgba8unorm?.takeIf { surface.supportedFormats.contains(it) }
        ?: TextureFormat.bgra8unorm?.takeIf { surface.supportedFormats.contains(it) }
        ?: surface.supportedFormats.first()

    override fun getCurrentTexture(): Texture {
        return surface.getCurrentTexture()
    }

    override fun close() {
        // Nothing to do here
    }
}