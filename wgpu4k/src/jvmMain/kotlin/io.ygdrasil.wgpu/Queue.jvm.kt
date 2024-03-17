package io.ygdrasil.wgpu

import com.sun.jna.Memory
import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.mapper.imageCopyTextureTaggedMapper
import java.awt.image.BufferedImage
import java.awt.image.DataBufferByte
import java.io.File
import javax.imageio.ImageIO


actual class Queue(internal val handler: WGPUQueue) {

    actual fun submit(commandsBuffer: Array<CommandBuffer>) {
        wgpuQueueSubmit(
            handler,
            NativeLong(commandsBuffer.size.toLong()),
            commandsBuffer.map { it.handler }.toTypedArray()
        )
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

    actual fun copyExternalImageToTexture(
        source: ImageCopyExternalImage,
        destination: ImageCopyTextureTagged,
        copySize: GPUIntegerCoordinates
    ) {
        val image = (source.source as? ImageBitmapHolder)
        if (image == null) error("ImageBitmapHolder required as source")

        wgpuQueueWriteTexture(
            handler,
            imageCopyTextureTaggedMapper.map(destination),
            image.data,
            (image.width * image.bytePerPixel * image.height).toNativeLong(),
            WGPUTextureDataLayout().apply {
                offset = 0
                bytesPerRow = image.width * image.bytePerPixel
                rowsPerImage = image.height
            },
            WGPUExtent3D().also {
                it.width = image.width
                it.height = image.height
                it.depthOrArrayLayers = 1
            }
        ) //TODO
    }


    private fun FloatArray.toBuffer(dataOffset: GPUSize64): Pointer {
        //Multiply by 4 because of 4 bytes per float
        return Memory(size * 4L).apply {
            write(0L, this@toBuffer, dataOffset.toInt(), size)
        }
    }

}

actual class ImageBitmapHolder(bufferedImage: BufferedImage) : DrawableHolder {

    actual val width: Int = bufferedImage.width
    actual val height: Int = bufferedImage.height
    val bytePerPixel: Int = 4
    val data: Memory

    init {
        val dataAsBytes = if (bytePerPixel == bufferedImage.colorModel.pixelSize / 8) {
            val dataBuffer = (bufferedImage.raster.dataBuffer as? DataBufferByte)
            check(dataBuffer is DataBufferByte) { "fail to get data" }
            dataBuffer.data
        } else {
            error("only 32 bit image are supported right now, use fun imageBitmapHolder(bufferedImage: BufferedImage) to create a ImageBitmapHolder")
        }
        check(dataAsBytes.size == width * height * bytePerPixel) { "data buffer size not matching expected value" }
        data = Memory((width * height * bytePerPixel).toLong())
        data.write(0, dataAsBytes, 0, dataAsBytes.size)
    }


}

actual sealed interface DrawableHolder

/**
 * quick and dirty method to convert data to 32bit if needed
 * find a more suitable way to do this
 */
fun imageBitmapHolder(bufferedImage: BufferedImage): ImageBitmapHolder {
    return if (4 == bufferedImage.colorModel.pixelSize / 8) {
        return ImageBitmapHolder(bufferedImage)
    } else {
        val newBufferedImage = bufferedImage.convertTo32()
        val tempFile = File.createTempFile("image", "32b")
            .also { it.deleteOnExit() }
        check(ImageIO.write(newBufferedImage, "png", tempFile)) { "fail to write image $${tempFile.absolutePath}" }
        imageBitmapHolder(ImageIO.read(tempFile))
    }
}

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
