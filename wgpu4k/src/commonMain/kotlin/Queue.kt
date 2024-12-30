package io.ygdrasil.webgpu

expect class Queue {

    fun submit(commandsBuffer: List<CommandBuffer> = listOf())

    fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: ShortArray,
        dataOffset: GPUSize64 = 0u,
        size: GPUSize64 = data.size.toULong(),
    )

    fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64 = 0u,
        size: GPUSize64 = data.size.toULong(),
    )

    fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: IntArray,
        dataOffset: GPUSize64 = 0u,
        size: GPUSize64 = data.size.toULong(),
    )

    fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: ByteArray,
        dataOffset: GPUSize64 = 0u,
        size: GPUSize64 = data.size.toULong(),
    )

    fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: DoubleArray,
        dataOffset: GPUSize64 = 0u,
        size: GPUSize64 = data.size.toULong(),
    )

    fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: LongArray,
        dataOffset: GPUSize64 = 0u,
        size: GPUSize64 = data.size.toULong(),
    )

    fun writeTexture(
        destination: ImageCopyTexture,
        data: FloatArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    )

    fun writeTexture(
        destination: ImageCopyTexture,
        data: DoubleArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    )

    fun writeTexture(
        destination: ImageCopyTexture,
        data: ByteArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    )

    fun writeTexture(
        destination: ImageCopyTexture,
        data: ShortArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    )

    fun writeTexture(
        destination: ImageCopyTexture,
        data: IntArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    )

    fun writeTexture(
        destination: ImageCopyTexture,
        data: LongArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    )
    
    fun copyExternalImageToTexture(
        source: ImageCopyExternalImage,
        destination: ImageCopyTextureTagged,
        copySize: GPUIntegerCoordinates,
    )

}

expect sealed interface DrawableHolder
expect class ImageBitmapHolder : DrawableHolder, AutoCloseable {
    val width: UInt
    val height: UInt

    override fun close()
}

data class ImageCopyExternalImage(
    val source: DrawableHolder,
    /* ImageBitmap | ImageData | HTMLImageElement | HTMLVideoElement | VideoFrame | HTMLCanvasElement | OffscreenCanvas */
    val origin: GPUIntegerCoordinates = 0u to 0u,
    /* Iterable<GPUIntegerCoordinate>? | GPUOrigin2DDictStrict? */
    val flipY: Boolean = false,

    )

data class ImageCopyTextureTagged(
    val colorSpace: PredefinedColorSpace = PredefinedColorSpace.srgb,
    val premultipliedAlpha: Boolean = false,
    val texture: Texture,
    val mipLevel: GPUIntegerCoordinate = 0u,
    val origin: Origin3D = Origin3D(),
    val aspect: TextureAspect = TextureAspect.All,

    )

enum class PredefinedColorSpace(val value: String) {
    srgb("srgb"),
    displayp3("display-p3")
}


data class TextureDataLayout (
    val offset: GPUSize64,
    val bytesPerRow: GPUSize32? = null,
    val rowsPerImage: GPUSize32? = null
)