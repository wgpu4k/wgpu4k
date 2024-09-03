package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface
import io.ygdrasil.wgpu.internal.jna.WGPUExtent3D
import io.ygdrasil.wgpu.internal.jna.WGPUTextureDataLayout
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.internal.toNativeArray
import io.ygdrasil.wgpu.mapper.map
import java.lang.foreign.SegmentAllocator
import java.lang.foreign.ValueLayout

private val supportedFormatOncopyExternalImageToTexture =
    listOf(TextureFormat.rgba8unorm, TextureFormat.rgba8unormsrgb)

actual class Queue(val handler: Long) {

    actual fun submit(commandsBuffer: List<CommandBuffer>) = scoped { arena ->
        if (commandsBuffer.isNotEmpty()) {

            val commands = commandsBuffer.map { it.handler }.toNativeArray(arena.arena).toAddress()

            JnaInterface.wgpuQueueSubmit(
                handler,
                commandsBuffer.size.toLong(),
                commands
            )
        } else {

            JnaInterface.wgpuQueueSubmit(
                handler,
                0L,
                0L
            )
        }
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) = scoped { arena ->
        JnaInterface.wgpuQueueWriteBuffer(
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
    ) = scoped { arena ->
        JnaInterface.wgpuQueueWriteBuffer(
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
    ) = scoped { arena ->
        check(destination.texture.format in supportedFormatOncopyExternalImageToTexture) {
            "(${
                supportedFormatOncopyExternalImageToTexture.map { it.actualName }.joinToString(", ")
            })are the only supported texture format supported, found ${destination.texture.format}"
        }

        val image = (source.source as? ImageBitmapHolder)
            ?: error("ImageBitmapHolder required as source")

        val bytePerPixel = destination.texture.format.getBytesPerPixel()

        JnaInterface.wgpuQueueWriteTexture(
            handler,
            arena.map(destination),
            image.data.toBuffer(0L, arena),
            (image.width * bytePerPixel * image.height).toLong(),
            WGPUTextureDataLayout.allocate(arena).also { dataLayout ->
                WGPUTextureDataLayout.offset(dataLayout, 0)
                WGPUTextureDataLayout.bytesPerRow(dataLayout, image.width * bytePerPixel)
                WGPUTextureDataLayout.rowsPerImage(dataLayout, image.height)
            }.pointer.toAddress(),
            WGPUExtent3D.allocate(arena).also { size3D ->
                WGPUExtent3D.width(size3D, image.width)
                WGPUExtent3D.height(size3D, image.height)
                WGPUExtent3D.depthOrArrayLayers(size3D, 1)
            }.pointer.toAddress()
        )

    }

    private fun ByteArray.toBuffer(dataOffset: GPUSize64, arena: SegmentAllocator): Long {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocateFrom(ValueLayout.JAVA_BYTE, this).pointer.toAddress()
    }

    private fun FloatArray.toBuffer(dataOffset: GPUSize64, arena: SegmentAllocator): Long {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocateFrom(ValueLayout.JAVA_FLOAT, this).pointer.toAddress()
    }

    private fun IntArray.toBuffer(dataOffset: GPUSize64, arena: SegmentAllocator): Long {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocateFrom(ValueLayout.JAVA_INT, this).pointer.toAddress()
    }
}


actual sealed interface DrawableHolder

actual class ImageBitmapHolder(
    val data: ByteArray,
    actual val width: Int,
    actual val height: Int
) : DrawableHolder