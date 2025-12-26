package io.ygdrasil.webgpu

@Deprecated("Will be removed in the next version and replace later when supported officially on native platform")
expect sealed interface DrawableHolder

@Deprecated("Will be removed in the next version and replace later when supported officially on native platform")
expect class ImageBitmapHolder : @Suppress("DEPRECATION") DrawableHolder, AutoCloseable {
    val width: UInt
    val height: UInt

    override fun close()
}

@Deprecated("Will be removed in the next version and replace later when supported officially on native platform")
data class ImageCopyExternalImage(
    val source: @Suppress("DEPRECATION") DrawableHolder,
    /* ImageBitmap | ImageData | HTMLImageElement | HTMLVideoElement | VideoFrame | HTMLCanvasElement | OffscreenCanvas */
    val origin: GPUOrigin2D? = null,
    /* Iterable<GPUIntegerCoordinate>? | GPUOrigin2DDictStrict? */
    val flipY: Boolean = false
)

@Deprecated("Will be removed in the next version and replace later when supported officially on native platform")
data class ImageCopyTextureTagged(
    val colorSpace: PredefinedColorSpace = PredefinedColorSpace.srgb,
    val premultipliedAlpha: Boolean = false,
    val texture: GPUTexture,
    val mipLevel: GPUIntegerCoordinate = 0u,
    val origin: GPUOrigin3D? = null,
    val aspect: GPUTextureAspect = GPUTextureAspect.All
)

@Deprecated("Will be removed in the next version and replace later when supported officially on native platform")
expect fun GPUQueue.copyExternalImageToTexture(
    @Suppress("DEPRECATION") source: ImageCopyExternalImage,
    @Suppress("DEPRECATION") destination: ImageCopyTextureTagged,
    copySize: GPUExtent3D
)

@Deprecated("Use writeBuffer with ArrayBuffer instead")
expect fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: ShortArray,
    dataOffset: GPUSize64 = 0u,
    size: GPUSize64 = data.size.toULong(),
)

@Deprecated("Use writeBuffer with ArrayBuffer instead")
expect fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: FloatArray,
    dataOffset: GPUSize64 = 0u,
    size: GPUSize64 = data.size.toULong(),
)

@Deprecated("Use writeBuffer with ArrayBuffer instead")
expect fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: IntArray,
    dataOffset: GPUSize64 = 0u,
    size: GPUSize64 = data.size.toULong(),
)
