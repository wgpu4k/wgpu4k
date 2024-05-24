package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.internal.jvm.panama.webgpu_h
import io.ygdrasil.wgpu.mapper.map
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

actual class Queue(internal val handler: MemorySegment) {

    actual fun submit(commandsBuffer: Array<CommandBuffer>) = confined { arena ->
        if (commandsBuffer.isNotEmpty()) {

            val commands = commandsBuffer.map { it.handler }.toPointerArray(arena)

            webgpu_h.wgpuQueueSubmit(
                handler,
                commandsBuffer.size.toLong(),
                commands
            )
        } else {

            webgpu_h.wgpuQueueSubmit(
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
        webgpu_h.wgpuQueueWriteBuffer(
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
        webgpu_h.wgpuQueueWriteBuffer(
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
        assert(destination.texture.format == TextureFormat.rgba8unorm) {
            error("rgba8unorm is the only supported texture format supported")
        }

        val image = (source.source as? ImageBitmapHolder)
        if (image == null) error("ImageBitmapHolder required as source")

        val bytePerPixel = destination.texture.format.getBytesPerPixel()

        webgpu_h.wgpuQueueWriteTexture(
            handler,
            arena.map(destination),
            image.data,
            (image.width * bytePerPixel * image.height).toLong(),
            WGPUTextureDataLayout().apply {
                offset = 0
                bytesPerRow = image.width * bytePerPixel
                rowsPerImage = image.height
            }.also { it.write() }.pointer.toMemory(),
            WGPUExtent3D().also {
                it.width = image.width
                it.height = image.height
                it.depthOrArrayLayers = 1
            }.also { it.write() }.pointer.toMemory()
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
