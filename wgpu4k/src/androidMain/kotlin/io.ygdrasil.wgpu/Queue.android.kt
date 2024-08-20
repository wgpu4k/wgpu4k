package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

private val supportedFormatOncopyExternalImageToTexture =
    listOf(TextureFormat.rgba8unorm, TextureFormat.rgba8unormsrgb)

actual class Queue(val handler: Long) {

    actual fun submit(commandsBuffer: List<CommandBuffer>) {
        JniInterface.instance.wgpuQueueSubmit(
            handler,
            commandsBuffer.size.toLong(),
            commandsBuffer.map { it.handler }
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) {
        JniInterface.instance.wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data,
            dataOffset,
            (size * Float.SIZE_BYTES)
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: IntArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) {
        JniInterface.instance.wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data,
            dataOffset,
            (size * Float.SIZE_BYTES)
        )
    }

    actual fun copyExternalImageToTexture(
        source: ImageCopyExternalImage,
        destination: ImageCopyTextureTagged,
        copySize: GPUIntegerCoordinates
    ) {
        check(destination.texture.format in supportedFormatOncopyExternalImageToTexture) {
            "(${
                supportedFormatOncopyExternalImageToTexture.map { it.actualName }.joinToString(", ")
            })are the only supported texture format supported, found ${destination.texture.format}"
        }

        val image = (source.source as? ImageBitmapHolder)
            ?: error("ImageBitmapHolder required as source")

        val bytePerPixel = destination.texture.format.getBytesPerPixel()

        JniInterface.instance.wgpuQueueWriteTexture(
            handler,
            destination,
            image.data,
            (image.width * bytePerPixel * image.height).toLong(),
            image.width,
            image.height,
            bytePerPixel
        )

    }

}

actual sealed interface DrawableHolder

actual class ImageBitmapHolder(
    val data: ByteArray,
    actual val width: Int,
    actual val height: Int
) : DrawableHolder