package io.ygdrasil.wgpu

class TextureRenderingContext(
    override val width: Int,
    override val height: Int,
    override val textureFormat: TextureFormat,
    device: Device,
) : RenderingContext {

    private val texture: Texture

    init {
        texture = device.createTexture(
            TextureDescriptor(
                label = "render texture",
                size = Size3D(256, 256),
                format = textureFormat,
                usage = setOf(TextureUsage.renderattachment, TextureUsage.copysrc)
            )
        )
    }

    override fun getCurrentTexture(): Texture {
        return texture
    }

    override fun close() {
        texture.close()
    }

}