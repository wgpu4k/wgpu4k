@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map
import kotlin.js.ExperimentalWasmJsInterop

actual class ImageBitmapHolder(
    actual val width: GPUSize32,
    actual val height: GPUSize32,
    val data: ArrayBuffer,
) : DrawableHolder, AutoCloseable {
    actual override fun close() {
        // Nothing to do
    }
}

actual sealed interface DrawableHolder

actual fun GPUQueue.copyExternalImageToTexture(
    source: @Suppress("DEPRECATION") ImageCopyExternalImage,
    destination: @Suppress("DEPRECATION") ImageCopyTextureTagged,
    copySize: GPUExtent3D
) {
    val handler = (this as Queue).handler
    val image = (source.source as? ImageBitmapHolder)
        ?: error("ImageBitmapHolder required as source")

    val bytePerPixel = destination.texture.format.getBytesPerPixel()

    handler.writeTexture(
        createJsObject<WGPUTexelCopyTextureInfo>().apply {
            texture = (destination.texture as Texture).handler
            mipLevel = destination.mipLevel.asJsNumber()
            destination.origin?.let { origin -> this.origin = map(origin) }
            aspect = destination.aspect.value
        },
        (image.data as WebArrayBuffer).buffer,
        createJsObject<WGPUTexelCopyBufferLayout>().apply {
            offset = 0uL.asJsNumber()
            bytesPerRow = (image.width * bytePerPixel).asJsNumber()
            rowsPerImage = image.height.asJsNumber()
        },
        createJsObject<WGPUExtent3D>().apply {
            width = image.width.asJsNumber()
            height = image.height.asJsNumber()
            depthOrArrayLayers = 1u.asJsNumber()
        }
    )

}

actual fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: ShortArray,
    dataOffset: GPUSize64,
    size: GPUSize64
) {
    val handler = (this as Queue).handler
    handler.writeBuffer(
        (buffer as Buffer).handler,
        bufferOffset.asJsNumber(),
        (ArrayBuffer.of(data) as WebArrayBuffer).buffer,
        dataOffset.asJsNumber(),
        (Short.SIZE_BYTES.toULong() * size).asJsNumber()
    )
}

actual fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: FloatArray,
    dataOffset: GPUSize64,
    size: GPUSize64
) {
    val handler = (this as Queue).handler
    handler.writeBuffer(
        (buffer as Buffer).handler,
        bufferOffset.asJsNumber(),
        (ArrayBuffer.of(data) as WebArrayBuffer).buffer,
        dataOffset.asJsNumber(),
        (Float.SIZE_BYTES.toULong() * size).asJsNumber()
    )
}

actual fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: IntArray,
    dataOffset: GPUSize64,
    size: GPUSize64
) {
    val handler = (this as Queue).handler
    handler.writeBuffer(
        (buffer as Buffer).handler,
        bufferOffset.asJsNumber(),
        (ArrayBuffer.of(data) as WebArrayBuffer).buffer,
        dataOffset.asJsNumber(),
        (Int.SIZE_BYTES.toULong() * size).asJsNumber()
    )
}
