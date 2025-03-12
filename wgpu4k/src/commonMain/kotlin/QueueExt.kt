package io.ygdrasil.webgpu

@Deprecated("Will be removed in the next version and replace later when supported officially on native platform")
expect sealed interface DrawableHolder

@Deprecated("Will be removed in the next version and replace later when supported officially on native platform")
expect class ImageBitmapHolder : DrawableHolder, AutoCloseable {
    val width: UInt
    val height: UInt

    override fun close()
}

@Deprecated("Will be removed in the next version and replace later when supported officially on native platform")
data class ImageCopyExternalImage(
    val source: DrawableHolder,
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
    source: ImageCopyExternalImage,
    destination: ImageCopyTextureTagged,
    copySize: GPUExtent3D
)

@Deprecated("Use writeTexture with ArrayBuffer instead")
expect fun GPUQueue.writeTexture(
    destination: GPUTexelCopyTextureInfo,
    data: FloatArray,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D,
)

@Deprecated("Use writeTexture with ArrayBuffer instead")
expect fun GPUQueue.writeTexture(
    destination: GPUTexelCopyTextureInfo,
    data: DoubleArray,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D,
)

@Deprecated("Use writeTexture with ArrayBuffer instead")
expect fun GPUQueue.writeTexture(
    destination: GPUTexelCopyTextureInfo,
    data: ByteArray,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D,
)

@Deprecated("Use writeTexture with ArrayBuffer instead")
expect fun GPUQueue.writeTexture(
    destination: GPUTexelCopyTextureInfo,
    data: ShortArray,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D,
)

@Deprecated("Use writeTexture with ArrayBuffer instead")
expect fun GPUQueue.writeTexture(
    destination: GPUTexelCopyTextureInfo,
    data: IntArray,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D,
)

@Deprecated("Use writeTexture with ArrayBuffer instead")
expect fun GPUQueue.writeTexture(
    destination: GPUTexelCopyTextureInfo,
    data: LongArray,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D,
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

@Deprecated("Use writeBuffer with ArrayBuffer instead")
expect fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: ByteArray,
    dataOffset: GPUSize64 = 0u,
    size: GPUSize64 = data.size.toULong(),
)

@Deprecated("Use writeBuffer with ArrayBuffer instead")
expect fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: DoubleArray,
    dataOffset: GPUSize64 = 0u,
    size: GPUSize64 = data.size.toULong(),
)

@Deprecated("Use writeBuffer with ArrayBuffer instead")
expect fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: LongArray,
    dataOffset: GPUSize64 = 0u,
    size: GPUSize64 = data.size.toULong(),
)