package io.ygdrasil.wgpu

import com.sun.jna.Memory
import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.mapper.imageCopyTextureTaggedMapper
import java.awt.image.BufferedImage


actual class Queue(internal val handler: WGPUQueue) {

    actual fun submit(commandsBuffer: Array<CommandBuffer>) {
        if (commandsBuffer.isNotEmpty()) {
            wgpuQueueSubmit(
                handler,
                NativeLong(commandsBuffer.size.toLong()),
                commandsBuffer.map { it.handler }.toTypedArray()
            )
        } else {
            wgpuQueueSubmit(
                handler,
                NativeLong(0L),
                null
            )
        }
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) {
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data.toBuffer(dataOffset),
            (size * Float.SIZE_BYTES).toNativeLong()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: IntArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) {
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data.toBuffer(dataOffset),
            (size * Float.SIZE_BYTES).toNativeLong()
        )
    }

    actual fun copyExternalImageToTexture(
        source: ImageCopyExternalImage,
        destination: ImageCopyTextureTagged,
        copySize: GPUIntegerCoordinates
    ) {
        val image = (source.source as? ImageBitmapHolder)
        if (image == null) error("ImageBitmapHolder required as source")

        val bytePerPixel = destination.texture.format.getBytesPerPixel()

        val data = Memory((image.width * bytePerPixel * image.height).toLong())

        var white = true
        (0 until image.width).forEach { x ->
            (0 until image.height).forEach { y ->
                val rgb: Int = image.bufferedImage.getRGB(x, y)
                val red = (rgb shr 16) and 0xFF
                val green = (rgb shr 8) and 0xFF
                val blue = (rgb) and 0xFF
                val alpha = (rgb shr 24) and 0xFF
                val pixel = byteArrayOf(red.toByte(), green.toByte(), blue.toByte(), alpha.toByte())
                data.write((x + y * image.width) * bytePerPixel.toLong(), pixel, 0, pixel.size)
            }
            white = !white
        }

        wgpuQueueWriteTexture(
            handler,
            imageCopyTextureTaggedMapper.map(destination),
            data,
            (image.width * bytePerPixel * image.height).toNativeLong(),
            WGPUTextureDataLayout().apply {
                offset = 0
                bytesPerRow = image.width * bytePerPixel
                rowsPerImage = image.height
            },
            WGPUExtent3D().also {
                it.width = image.width
                it.height = image.height
                it.depthOrArrayLayers = 1
            }
        )

        data.dump()
    }


    private fun FloatArray.toBuffer(dataOffset: GPUSize64): Pointer {
        //Multiply by 4 because of 4 bytes per float
        return Memory(size * 4L).apply {
            write(0L, this@toBuffer, dataOffset.toInt(), size)
        }
    }

    private fun IntArray.toBuffer(dataOffset: GPUSize64): Pointer {
        //Multiply by 4 because of 4 bytes per float
        return Memory(size * 4L).apply {
            write(0L, this@toBuffer, dataOffset.toInt(), size)
        }
    }
}

actual class ImageBitmapHolder(val bufferedImage: BufferedImage) : DrawableHolder {

    actual val width: Int = bufferedImage.width
    actual val height: Int = bufferedImage.height


}

actual sealed interface DrawableHolder


fun BufferedImage.convertTo32(): BufferedImage {
    val w = width
    val h = height

    // Create a new BufferedImage of type ARGB
    val outputImg = BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB)

    // Get the graphics object from the new image
    val g = outputImg.createGraphics()

    // Draw the input image onto the new image
    g.drawImage(this, 0, 0, null)


    // Clean up
    g.dispose()

    // Return the new 32-bit image
    return outputImg
}
