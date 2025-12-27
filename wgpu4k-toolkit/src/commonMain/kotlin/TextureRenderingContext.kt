package io.ygdrasil.webgpu

class TextureRenderingContext(
    override val width: UInt,
    override val height: UInt,
    override val textureFormat: GPUTextureFormat,
    device: GPUDevice,
) : RenderingContext {

    private val texture: GPUTexture

    init {
        texture = device.createTexture(
            TextureDescriptor(
                label = "render texture",
                size = Extent3D(256u, 256u),
                format = textureFormat,
                usage = GPUTextureUsage.RenderAttachment or GPUTextureUsage.CopySrc or GPUTextureUsage.CopyDst
            )
        )
    }

    override fun getCurrentTexture(): GPUTexture {
        return texture
    }

    override fun close() {
        texture.close()
    }

}