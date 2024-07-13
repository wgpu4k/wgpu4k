package io.ygdrasil.wgpu


import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUExtent3D
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUTextureDataLayout
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import io.ygdrasil.wgpu.internal.jvm.toPointerArray
import io.ygdrasil.wgpu.mapper.map
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

private val supportedFormatOncopyExternalImageToTexture = listOf(TextureFormat.rgba8unorm, TextureFormat.rgba8unormsrgb)

actual class Queue(internal val handler: MemorySegment) {

    actual fun submit(commandsBuffer: List<CommandBuffer>) = confined { arena ->
        if (commandsBuffer.isNotEmpty()) {

            val commands = commandsBuffer.map { it.handler }.toPointerArray(arena)

            wgpu_h.wgpuQueueSubmit(
                handler,
                commandsBuffer.size.toLong(),
                commands
            )
        } else {

            wgpu_h.wgpuQueueSubmit(
                handler,
                0L,
                MemorySegment.NULL
            )
        }
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) = confined { arena ->
        wgpu_h.wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data.toBuffer(dataOffset, arena),
            (size * Float.SIZE_BYTES)
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: IntArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) = confined { arena ->
        wgpu_h.wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data.toBuffer(dataOffset, arena),
            (size * Float.SIZE_BYTES)
        )
    }

    actual fun copyExternalImageToTexture(
        source: ImageCopyExternalImage,
        destination: ImageCopyTextureTagged,
        copySize: GPUIntegerCoordinates
    ) = confined { arena ->
        check(destination.texture.format in supportedFormatOncopyExternalImageToTexture) {
            "(${supportedFormatOncopyExternalImageToTexture.map { it.actualName }.joinToString (", ")})are the only supported texture format supported, found ${destination.texture.format}"
        }

        val image = (source.source as? ImageBitmapHolder)
            ?: error("ImageBitmapHolder required as source")

        val bytePerPixel = destination.texture.format.getBytesPerPixel()

        wgpu_h.wgpuQueueWriteTexture(
            handler,
            arena.map(destination),
            image.data,
            (image.width * bytePerPixel * image.height).toLong(),
            WGPUTextureDataLayout.allocate(arena).also { dataLayout ->
                WGPUTextureDataLayout.offset(dataLayout, 0)
                WGPUTextureDataLayout.bytesPerRow(dataLayout, image.width * bytePerPixel)
                WGPUTextureDataLayout.rowsPerImage(dataLayout, image.height)
            },
            WGPUExtent3D.allocate(arena).also { size3D ->
                WGPUExtent3D.width(size3D, image.width)
                WGPUExtent3D.height(size3D, image.height)
                WGPUExtent3D.depthOrArrayLayers(size3D, 1)
            }
        )

    }


    private fun FloatArray.toBuffer(dataOffset: GPUSize64, arena: Arena): MemorySegment {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocateFrom(ValueLayout.JAVA_FLOAT, *this)
    }

    private fun IntArray.toBuffer(dataOffset: GPUSize64, arena: Arena): MemorySegment {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocateFrom(ValueLayout.JAVA_INT, *this)
    }
}

actual class ImageBitmapHolder(
    val arena: Arena,
    val data: MemorySegment,
    actual val width: Int,
    actual val height: Int
) : DrawableHolder, AutoCloseable {

    override fun close() {
        arena.close()
    }
}

actual sealed interface DrawableHolder
