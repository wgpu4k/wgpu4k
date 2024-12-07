package io.ygdrasil.wgpu

class TextureRenderingContext(
    override val width: UInt,
    override val height: UInt,
    override val textureFormat: TextureFormat,
    device: Device,
) : RenderingContext {

    private val texture: Texture

    init {
        texture = device.createTexture(
            TextureDescriptor(
                label = "render texture",
                size = Size3D(256u, 256u),
                format = textureFormat,
                usage = setOf(TextureUsage.renderAttachment, TextureUsage.copySrc, TextureUsage.copyDst)
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