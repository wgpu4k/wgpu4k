package io.ygdrasil.wgpu

import com.sun.jna.Memory
import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.mapper.imageCopyTextureTaggedMapper
import java.awt.image.BufferedImage
import java.awt.image.DataBufferByte


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
        val dataBuffer = (bufferedImage.raster.dataBuffer as? DataBufferByte)
        check(dataBuffer is DataBufferByte) { "fail to get data" }
        check(dataBuffer.size == width * height * bytePerPixel) { "data buffer size not matching expected value" }
        data = Memory((width * height * bytePerPixel).toLong())
        data.write(0, dataBuffer.data, 0, dataBuffer.data.size)
    }


}

actual sealed interface DrawableHolder