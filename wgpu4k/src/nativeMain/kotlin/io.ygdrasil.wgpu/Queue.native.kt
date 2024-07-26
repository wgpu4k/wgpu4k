@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.toPointerArray
import io.ygdrasil.wgpu.mapper.map
import kotlinx.cinterop.*
import webgpu.*

private val supportedFormatOncopyExternalImageToTexture = listOf(TextureFormat.rgba8unorm, TextureFormat.rgba8unormsrgb)

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

    actual fun copyExternalImageToTexture(
        source: ImageCopyExternalImage,
        destination: ImageCopyTextureTagged,
        copySize: GPUIntegerCoordinates
    ) = memScoped {
        check(destination.texture.format in supportedFormatOncopyExternalImageToTexture) {
            "(${
                supportedFormatOncopyExternalImageToTexture.map { it.actualName }.joinToString(", ")
            })are the only supported texture format supported, found ${destination.texture.format}"
        }

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

    private fun IntArray.toBuffer(dataOffset: GPUSize64, arena: ArenaBase): CValuesRef<*> {
        if (dataOffset != 0L) error("data offset not yet supported") // TODO support dataOffset
        return arena.allocArray<IntVar>(size).also {
            forEachIndexed { index, value -> it[index] = value }
        }
    }
}

actual sealed interface DrawableHolder

actual class ImageBitmapHolder(
    val arena: Arena,
    val data: CValuesRef<*>,
    actual val width: Int,
    actual val height: Int
) : DrawableHolder, AutoCloseable {

    override fun close() {
        arena.clear()
    }
}