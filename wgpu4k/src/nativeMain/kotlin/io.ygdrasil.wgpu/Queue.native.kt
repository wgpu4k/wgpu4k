@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.toPointerArray
import io.ygdrasil.wgpu.mapper.map
import kotlinx.cinterop.*
import webgpu.WGPUExtent3D
import webgpu.WGPUQueue
import webgpu.WGPUTextureDataLayout
import webgpu.wgpuQueueSubmit
import webgpu.wgpuQueueWriteBuffer
import webgpu.wgpuQueueWriteTexture

actual class Queue(internal val handler: WGPUQueue) {

    actual fun submit(commandsBuffer: List<CommandBuffer>) = memScoped {
        if (commandsBuffer.isNotEmpty()) {
            val commands = commandsBuffer.map { it.handler }.toPointerArray(this)
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
    ) = memScoped {
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset.toULong(),
            data.toBuffer(dataOffset, this),
            (size * Short.SIZE_BYTES).toULong()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) = memScoped {
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset.toULong(),
            data.toBuffer(dataOffset, this),
            (size * Float.SIZE_BYTES).toULong()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: IntArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) = memScoped {
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset.toULong(),
            data.toBuffer(dataOffset, this),
            (size * Float.SIZE_BYTES).toULong()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: ByteArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) = memScoped {
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset.toULong(),
            data.toBuffer(dataOffset, this),
            (size * Byte.SIZE_BYTES).toULong()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: DoubleArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) = memScoped {
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset.toULong(),
            data.toBuffer(dataOffset, this),
            (size * Double.SIZE_BYTES).toULong()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: LongArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) = memScoped {
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset.toULong(),
            data.toBuffer(dataOffset, this),
            (size * Long.SIZE_BYTES).toULong()
        )
    }
    actual fun writeTexture(
        destination: ImageCopyTexture,
        data: FloatArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    ) = memScoped {
        wgpuQueueWriteTexture(
            handler,
            map(destination).ptr,
            data.toBuffer(0, this),
            data.size.toULong(),
            map(dataLayout).ptr,
            map(size).ptr
        )
    }

    actual fun copyExternalImageToTexture(
        source: ImageCopyExternalImage,
        destination: ImageCopyTextureTagged,
        copySize: GPUIntegerCoordinates
    ) = memScoped {

        val image = (source.source as? ImageBitmapHolder)
            ?: error("ImageBitmapHolder required as source")

        val bytePerPixel = destination.texture.format.getBytesPerPixel()

        wgpuQueueWriteTexture(
            handler,
            map(destination).ptr,
            image.data,
            (image.width * bytePerPixel * image.height).toULong(),
            alloc<WGPUTextureDataLayout>().also { dataLayout ->
                dataLayout.offset = 0u
                dataLayout.bytesPerRow = (image.width * bytePerPixel).toUInt()
                dataLayout.rowsPerImage = image.height.toUInt()
            }.ptr,
            alloc<WGPUExtent3D>().also { size3D ->
                size3D.width = image.width.toUInt()
                size3D.height = image.height.toUInt()
                size3D.depthOrArrayLayers = 1u
            }.ptr
        )

    }

    private fun FloatArray.toBuffer(dataOffset: GPUSize64, arena: ArenaBase): CValuesRef<*> {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocArray<FloatVar>(size).also {
            forEachIndexed { index, value -> it[index] = value }
        }
    }

    private fun DoubleArray.toBuffer(dataOffset: GPUSize64, arena: ArenaBase): CValuesRef<*> {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocArray<DoubleVar>(size).also {
            forEachIndexed { index, value -> it[index] = value }
        }
    }

    private fun LongArray.toBuffer(dataOffset: GPUSize64, arena: ArenaBase): CValuesRef<*> {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocArray<LongVar>(size).also {
            forEachIndexed { index, value -> it[index] = value }
        }
    }

    private fun IntArray.toBuffer(dataOffset: GPUSize64, arena: ArenaBase): CValuesRef<*> {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocArray<IntVar>(size).also {
            forEachIndexed { index, value -> it[index] = value }
        }
    }

    private fun ShortArray.toBuffer(dataOffset: GPUSize64, arena: ArenaBase): CValuesRef<*> {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocArray<ShortVar>(size).also {
            forEachIndexed { index, value -> it[index] = value }
        }
    }

    private fun ByteArray.toBuffer(dataOffset: GPUSize64, arena: ArenaBase): CValuesRef<*> {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocArray<ByteVar>(size).also {
            forEachIndexed { index, value -> it[index] = value }
        }
    }
}

actual sealed interface DrawableHolder

actual class ImageBitmapHolder(
    val data: CValuesRef<*>,
    actual val width: Int,
    actual val height: Int
) : DrawableHolder, AutoCloseable {

    actual override fun close() {
        // Nothing to do
    }
}