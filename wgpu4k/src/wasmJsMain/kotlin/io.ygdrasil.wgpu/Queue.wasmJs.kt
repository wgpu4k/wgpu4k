package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.*
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Int16Array
import org.khronos.webgl.Int32Array

actual class Queue(internal val handler: GPUQueue) {

    actual fun submit(commandsBuffer: List<CommandBuffer>) {
        handler.submit(commandsBuffer.mapJsArray { it.handler })
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: ShortArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) {
        handler.writeBuffer(
            buffer.handler,
            bufferOffset.toJsNumber(),
            Int16Array(data.mapJsArray { it.toJsNumber() }),
            dataOffset.toJsNumber(),
            size.toJsNumber()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) {
        handler.writeBuffer(
            buffer.handler,
            bufferOffset.toJsNumber(),
            Float32Array(data.mapJsArray { it.toJsNumber() }),
            dataOffset.toJsNumber(),
            size.toJsNumber()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: IntArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) {
        handler.writeBuffer(
            buffer.handler,
            bufferOffset.toJsNumber(),
            Int32Array(data.mapJsArray { it.toJsNumber() }),
            dataOffset.toJsNumber(),
            size.toJsNumber()
        )
    }

    actual fun copyExternalImageToTexture(
        source: ImageCopyExternalImage,
        destination: ImageCopyTextureTagged,
        copySize: GPUIntegerCoordinates,
    ) {

        val image = (source.source as? ImageBitmapHolder)
            ?: error("ImageBitmapHolder required as source")

        val bytePerPixel = destination.texture.format.getBytesPerPixel()

        handler.writeTexture(
            createJsObject<GPUImageCopyTexture>().apply {
                texture = destination.texture.handler
                mipLevel = destination.mipLevel
                origin = destination.origin.toArray().mapJsArray { it.toJsNumber() }
                aspect = destination.aspect.stringValue
            },
            image.data.toInt8Array().buffer,
            createJsObject<GPUImageDataLayout>().apply {
                offset = 0.toJsNumber()
                bytesPerRow = image.width * bytePerPixel
                rowsPerImage = image.height
            },
            createJsObject<GPUExtent3DDict>().apply {
                width = image.width
                height = image.height
                depthOrArrayLayers = 1
            }
        )
    }
}

actual sealed interface DrawableHolder
actual class ImageBitmapHolder(
    actual val width: Int,
    actual val height: Int,
    val data: ByteArray,
) : DrawableHolder, AutoCloseable {

    actual override fun close() {
        // Nothing to do
    }
}