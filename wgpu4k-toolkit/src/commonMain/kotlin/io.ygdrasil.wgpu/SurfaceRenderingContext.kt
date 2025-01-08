package io.ygdrasil.webgpu

class SurfaceRenderingContext(
    private val surface: Surface
) : RenderingContext {

    override val width: UInt
        get() = surface.width
    override val height: UInt
        get() = surface.height
    override val textureFormat: TextureFormat
        get() = surface.preferredCanvasFormat
        /*
            https://developer.mozilla.org/en-US/docs/Web/API/GPU/getPreferredCanvasFormat
            rgba8unorm or bgra8unorm are default format supported on web
         */
        ?: TextureFormat.RGBA8Unorm?.takeIf { surface.supportedFormats.contains(it) }
        ?: TextureFormat.BGRA8Unorm?.takeIf { surface.supportedFormats.contains(it) }
        ?: surface.supportedFormats.first()

    override fun getCurrentTexture(): Texture {
        return surface.getCurrentTexture().texture
    }

    override fun close() {
        // Nothing to do here
    }
}