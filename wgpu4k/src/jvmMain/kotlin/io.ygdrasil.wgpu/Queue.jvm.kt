package io.ygdrasil.wgpu


import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.toPointerArray
import io.ygdrasil.wgpu.mapper.map
import webgpu.WGPUQueue
import webgpu.wgpuQueueSubmit
import webgpu.wgpuQueueWriteBuffer
import webgpu.wgpuQueueWriteTexture
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

actual class Queue(internal val handler: WGPUQueue) {

    actual fun submit(commandsBuffer: List<CommandBuffer>) = confined { arena ->
        if (commandsBuffer.isNotEmpty()) {

            val commands = commandsBuffer.map { it.handler }.toPointerArray(arena)

            wgpuQueueSubmit(
                handler,
                commandsBuffer.size.toULong(),
                commands
            )
        } else {

            wgpuQueueSubmit(
                handler,
                0uL,
                null
            )
        }
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: ShortArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) = confined { arena ->
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data.toBuffer(dataOffset, arena),
            (size * Short.SIZE_BYTES)
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) = confined { arena ->
        wgpuQueueWriteBuffer(
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
        wgpuQueueWriteBuffer(
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
        data: ByteArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) = confined { arena ->
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data.toBuffer(dataOffset, arena),
            size * Byte.SIZE_BYTES
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: DoubleArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) = confined { arena ->
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data.toBuffer(dataOffset, arena),
            size * Double.SIZE_BYTES
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: LongArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) = confined { arena ->
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data.toBuffer(dataOffset, arena),
            size * Long.SIZE_BYTES
        )
    }

    actual fun writeTexture(
        destination: ImageCopyTexture,
        data: FloatArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    ) = confined { arena ->
        wgpuQueueWriteTexture(
            handler,
            arena.map(destination),
            data.toBuffer(0, arena),
            Float.SIZE_BYTES * data.size.toLong(),
            arena.map(dataLayout),
            arena.map(size)
        )
    }

    actual fun writeTexture(
        destination: ImageCopyTexture,
        data: DoubleArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    ) = confined { arena ->
        wgpuQueueWriteTexture(
            handler,
            arena.map(destination),
            data.toBuffer(0, arena),
            Double.SIZE_BYTES * data.size.toLong(),
            arena.map(dataLayout),
            arena.map(size)
        )
    }

    actual fun writeTexture(
        destination: ImageCopyTexture,
        data: ByteArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    ) = confined { arena ->
        wgpuQueueWriteTexture(
            handler,
            arena.map(destination),
            data.toBuffer(0, arena),
            Byte.SIZE_BYTES * data.size.toLong(),
            arena.map(dataLayout),
            arena.map(size)
        )
    }

    actual fun writeTexture(
        destination: ImageCopyTexture,
        data: ShortArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    ) = confined { arena ->
        wgpuQueueWriteTexture(
            handler,
            arena.map(destination),
            data.toBuffer(0, arena),
            Short.SIZE_BYTES * data.size.toLong(),
            arena.map(dataLayout),
            arena.map(size)
        )
    }

    actual fun writeTexture(
        destination: ImageCopyTexture,
        data: IntArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    ) = confined { arena ->
        wgpuQueueWriteTexture(
            handler,
            arena.map(destination),
            data.toBuffer(0, arena),
            Int.SIZE_BYTES * data.size.toLong(),
            arena.map(dataLayout),
            arena.map(size)
        )
    }

    actual fun writeTexture(
        destination: ImageCopyTexture,
        data: LongArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    ) = confined { arena ->
        wgpuQueueWriteTexture(
            handler,
            arena.map(destination),
            data.toBuffer(0, arena),
            Long.SIZE_BYTES * data.size.toLong(),
            arena.map(dataLayout),
            arena.map(size)
        )
    }

    actual fun copyExternalImageToTexture(
        source: ImageCopyExternalImage,
        destination: ImageCopyTextureTagged,
        copySize: GPUIntegerCoordinates
    ) = confined { arena ->

        val image = (source.source as? ImageBitmapHolder)
            ?: error("ImageBitmapHolder required as source")

        val bytePerPixel = destination.texture.format.getBytesPerPixel()

        wgpuQueueWriteTexture(
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

    private fun DoubleArray.toBuffer(dataOffset: GPUSize64, arena: Arena): MemorySegment {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocateFrom(ValueLayout.JAVA_DOUBLE, *this)
    }

    private fun FloatArray.toBuffer(dataOffset: GPUSize64, arena: Arena): MemorySegment {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocateFrom(ValueLayout.JAVA_FLOAT, *this)
    }

    private fun LongArray.toBuffer(dataOffset: GPUSize64, arena: Arena): MemorySegment {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocateFrom(ValueLayout.JAVA_LONG, *this)
    }

    private fun IntArray.toBuffer(dataOffset: GPUSize64, arena: Arena): MemorySegment {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocateFrom(ValueLayout.JAVA_INT, *this)
    }

    private fun ShortArray.toBuffer(dataOffset: GPUSize64, arena: Arena): MemorySegment {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocateFrom(ValueLayout.JAVA_SHORT, *this)
    }

    private fun ByteArray.toBuffer(dataOffset: GPUSize64, arena: Arena): MemorySegment {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocateFrom(ValueLayout.JAVA_BYTE, *this)
    }
}

actual class ImageBitmapHolder(
    val arena: Arena,
    val data: MemorySegment,
    actual val width: Int,
    actual val height: Int
) : DrawableHolder, AutoCloseable {

    actual override fun close() {
        arena.close()
    }
}

actual sealed interface DrawableHolder
