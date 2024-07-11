package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.*
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Int32Array
import org.khronos.webgl.Int8Array

actual class Queue(internal val handler: GPUQueue) {

    actual fun submit(commandsBuffer: List<CommandBuffer>) {
        handler.submit(commandsBuffer.mapJsArray { it.handler })
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: GPUSize64
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
        size: GPUSize64
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
        copySize: GPUIntegerCoordinates
    ) {
        if (destination.texture.format !in listOf(TextureFormat.rgba8unorm, TextureFormat.rgba8unormsrgb)) {
            error("rgba8unorm asnd rgba8unormsrgb are the only supported texture format supported")
        }

        val image = (source.source as? ImageBitmapHolder)
        if (image == null) error("ImageBitmapHolder required as source")

        val bytePerPixel = destination.texture.format.getBytesPerPixel()

        handler.writeTexture(
            createJsObject<GPUImageCopyTexture>().apply {
                texture = destination.texture.handler
                mipLevel = destination.mipLevel
                origin = destination.origin.toArray().mapJsArray { it.toJsNumber() }
                aspect= destination.aspect.stringValue
            },
            Int8Array(image.data.mapJsArray { it.toJsNumber() }).buffer,
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
    val data: ByteArray
) : DrawableHolder