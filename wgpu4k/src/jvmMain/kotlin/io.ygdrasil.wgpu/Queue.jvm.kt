package io.ygdrasil.wgpu

import com.sun.jna.Memory
import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.mapper.imageCopyTextureTaggedMapper

actual class Queue(internal val handler: WGPUQueue) {

    actual fun submit(commandsBuffer: Array<CommandBuffer>) {
        logUnitNative {
            "wgpuQueueSubmit" to listOf(
                handler,
                NativeLong(commandsBuffer.size.toLong()),
                commandsBuffer.map { it.handler }.toTypedArray()
            )
        }
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
        logUnitNative {
            "wgpuQueueWriteBuffer" to listOf(
                handler,
                buffer.handler,
                bufferOffset,
                data.toBuffer(dataOffset),
                (size * Float.SIZE_BYTES).toNativeLong()
            )
        }
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
        logUnitNative {
            "wgpuQueueWriteBuffer" to listOf(
                handler,
                buffer.handler,
                bufferOffset,
                data.toBuffer(dataOffset),
                (size * Float.SIZE_BYTES).toNativeLong()
            )
        }
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
        assert(destination.texture.format == TextureFormat.rgba8unorm) {
            error("rgba8unorm is the only supported texture format supported")
        }

        val image = (source.source as? ImageBitmapHolder)
        if (image == null) error("ImageBitmapHolder required as source")

        val bytePerPixel = destination.texture.format.getBytesPerPixel()

        wgpuQueueWriteTexture(
            handler,
            imageCopyTextureTaggedMapper.map(destination),
            image.data,
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


actual class ImageBitmapHolder(
    val data: Memory,
    actual val width: Int,
    actual val height: Int
) : DrawableHolder, AutoCloseable {

    override fun close() {
        data.dump()
    }
}

actual sealed interface DrawableHolder
