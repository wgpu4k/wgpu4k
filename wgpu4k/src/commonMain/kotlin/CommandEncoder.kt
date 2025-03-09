package io.ygdrasil.webgpu

expect class CommandEncoder : GPUCommandEncoder {

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

}

inline fun GPUCommandEncoder.beginRenderPass(
    descriptor: GPURenderPassDescriptor,
    then: GPURenderPassEncoder.() -> Unit
) {
    beginRenderPass(descriptor).apply(then)
}

data class ImageCopyTexture(
    val texture: Texture,
    val mipLevel: GPUIntegerCoordinate = 0u,
    val origin: Origin3D = Origin3D(0u, 0u),
    val aspect: TextureAspect = TextureAspect.All,
)

data class ImageCopyBuffer(
    val buffer: Buffer,
    val offset: GPUSize64,
    val bytesPerRow: GPUSize32,
    val rowsPerImage: GPUSize32,
)