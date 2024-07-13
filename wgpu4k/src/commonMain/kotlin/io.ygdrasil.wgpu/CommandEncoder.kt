package io.ygdrasil.wgpu

expect class CommandEncoder : AutoCloseable {

    fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder

    fun finish(): CommandBuffer

    fun copyTextureToTexture(
        source: ImageCopyTexture,
        destination: ImageCopyTexture,
        copySize: Size3D,
    )

    fun beginComputePass(descriptor: ComputePassDescriptor? = null): ComputePassEncoder

    fun copyTextureToBuffer(source: ImageCopyTexture, destination: ImageCopyBuffer, copySize: Size3D)

    fun copyBufferToTexture(source: ImageCopyBuffer, destination: ImageCopyTexture, copySize: Size3D)

    override fun close()
}

data class ImageCopyTexture(
    val texture: Texture,
    val mipLevel: GPUIntegerCoordinate = 0,
    val origin: Origin3D = Origin3D(0, 0),
    val aspect: TextureAspect = TextureAspect.all,
)

data class ImageCopyBuffer(
    val buffer: Buffer,
    val offset: GPUSize64,
    val bytesPerRow: GPUSize32,
    val rowsPerImage: GPUSize32,
)