package io.ygdrasil.webgpu

import ffi.ArrayHolder
import ffi.MemoryAllocator
import ffi.NativeAddress
import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUCommandBuffer
import io.ygdrasil.wgpu.WGPUQueue
import io.ygdrasil.wgpu.wgpuQueueSubmit
import io.ygdrasil.wgpu.wgpuQueueWriteBuffer
import io.ygdrasil.wgpu.wgpuQueueWriteTexture

actual class Queue(internal val handler: WGPUQueue) : GPUQueue {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override fun submit(commandBuffers: List<GPUCommandBuffer>)= memoryScope { scope ->
        if (commandBuffers.isNotEmpty()) {

            val commands = scope.bufferOfAddresses(commandBuffers.map { (it as CommandBuffer).handler.handler })
                .handler
                .let { ArrayHolder<WGPUCommandBuffer>(it) }

            wgpuQueueSubmit(
                handler,
                commandBuffers.size.toULong(),
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

    actual override suspend fun onSubmittedWorkDone(): Result<Unit> {
        TODO("Not yet implemented")
    }

    actual override fun writeBuffer(
        buffer: GPUBuffer,
        bufferOffset: GPUSize64,
        data: ArrayBuffer,
        dataOffset: GPUSize64,
        size: GPUSize64?
    ) {
        TODO("Not yet implemented")
    }

    actual override fun writeTexture(
        destination: GPUTexelCopyTextureInfo,
        data: ArrayBuffer,
        dataLayout: GPUTexelCopyBufferLayout,
        size: GPUExtent3D
    ) {
        TODO("Not yet implemented")
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: ShortArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) = memoryScope { scope ->
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data.toBuffer(dataOffset, scope),
            size * Short.SIZE_BYTES.toULong()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) = memoryScope { scope ->
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data.toBuffer(dataOffset, scope),
            size * Float.SIZE_BYTES.toULong()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: IntArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) = memoryScope { scope ->
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data.toBuffer(dataOffset, scope),
            size * Float.SIZE_BYTES.toULong()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: ByteArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) = memoryScope { scope ->
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data.toBuffer(dataOffset, scope),
            size * Byte.SIZE_BYTES.toULong()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: DoubleArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) = memoryScope { scope ->
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data.toBuffer(dataOffset, scope),
            size * Double.SIZE_BYTES.toULong()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: LongArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) = memoryScope { scope ->
        wgpuQueueWriteBuffer(
            handler,
            buffer.handler,
            bufferOffset,
            data.toBuffer(dataOffset, scope),
            size * Long.SIZE_BYTES.toULong()
        )
    }

    actual fun writeTexture(
        destination: GPUTexelCopyTextureInfo,
        data: FloatArray,
        dataLayout: GPUTexelCopyBufferLayout,
        size: GPUExtent3D,
    ) = memoryScope { scope ->
        wgpuQueueWriteTexture(
            handler,
            scope.map(destination),
            data.toBuffer(0u, scope),
            (Float.SIZE_BYTES * data.size).toULong(),
            scope.map(dataLayout),
            scope.map(size)
        )
    }

    actual fun writeTexture(
        destination: GPUTexelCopyTextureInfo,
        data: DoubleArray,
        dataLayout: GPUTexelCopyBufferLayout,
        size: GPUExtent3D,
    ) = memoryScope { scope ->
        wgpuQueueWriteTexture(
            handler,
            scope.map(destination),
            data.toBuffer(0u, scope),
            (Double.SIZE_BYTES * data.size).toULong(),
            scope.map(dataLayout),
            scope.map(size)
        )
    }

    actual fun writeTexture(
        destination: GPUTexelCopyTextureInfo,
        data: ByteArray,
        dataLayout: GPUTexelCopyBufferLayout,
        size: GPUExtent3D,
    ) = memoryScope { scope ->
        wgpuQueueWriteTexture(
            handler,
            scope.map(destination),
            data.toBuffer(0u, scope),
            (Byte.SIZE_BYTES * data.size).toULong(),
            scope.map(dataLayout),
            scope.map(size)
        )
    }

    actual fun writeTexture(
        destination: GPUTexelCopyTextureInfo,
        data: ShortArray,
        dataLayout: GPUTexelCopyBufferLayout,
        size: GPUExtent3D,
    ) = memoryScope { scope ->
        wgpuQueueWriteTexture(
            handler,
            scope.map(destination),
            data.toBuffer(0u, scope),
            (Short.SIZE_BYTES * data.size).toULong(),
            scope.map(dataLayout),
            scope.map(size)
        )
    }

    actual fun writeTexture(
        destination: GPUTexelCopyTextureInfo,
        data: IntArray,
        dataLayout: GPUTexelCopyBufferLayout,
        size: GPUExtent3D,
    ) = memoryScope { scope ->
        wgpuQueueWriteTexture(
            handler,
            scope.map(destination),
            data.toBuffer(0u, scope),
            (Int.SIZE_BYTES * data.size).toULong(),
            scope.map(dataLayout),
            scope.map(size)
        )
    }

    actual fun writeTexture(
        destination: GPUTexelCopyTextureInfo,
        data: LongArray,
        dataLayout: GPUTexelCopyBufferLayout,
        size: GPUExtent3D,
    ) = memoryScope { scope ->
        wgpuQueueWriteTexture(
            handler,
            scope.map(destination),
            data.toBuffer(0u, scope),
            (Long.SIZE_BYTES * data.size).toULong(),
            scope.map(dataLayout),
            scope.map(size)
        )
    }

    /*actual fun copyExternalImageToTexture(
        source: GPUImageCopyExternalImage,
        destination: GPUImageCopyTextureTagged,
        copySize: GPUIntegerCoordinates
    ) = memoryScope { scope ->

        val image = (source.source as? ImageBitmapHolder)
            ?: error("ImageBitmapHolder required as source")

        val bytePerPixel = destination.texture.format.getBytesPerPixel()

        wgpuQueueWriteTexture(
            handler,
            scope.map(destination),
            image.data,
            (image.width * bytePerPixel * image.height).toULong(),
            WGPUTexelCopyBufferLayout.allocate(scope).also { dataLayout ->
                dataLayout.offset = 0u
                dataLayout.bytesPerRow = image.width * bytePerPixel
                dataLayout.rowsPerImage = image.height
            },
            WGPUExtent3D.allocate(scope).also { size3D ->
                size3D.width = image.width
                size3D.height = image.height
                size3D.depthOrArrayLayers = 1u
            }
        )

    }*/

    private fun DoubleArray.toBuffer(dataOffset: GPUSize64, scope: MemoryAllocator): NativeAddress {
        val memorySize = (size.toULong() - dataOffset) * Double.SIZE_BYTES.toULong()
        return scope.allocateBuffer(memorySize)
            .also { buffer -> buffer.writeDoubles(this, dataOffset) }
            .handler
    }

    private fun FloatArray.toBuffer(dataOffset: GPUSize64, scope: MemoryAllocator): NativeAddress {
        val memorySize = (size.toULong() - dataOffset) * Float.SIZE_BYTES.toULong()
        return scope.allocateBuffer(memorySize)
            .also { buffer -> buffer.writeFloats(this, dataOffset) }
            .handler
    }

    private fun LongArray.toBuffer(dataOffset: GPUSize64, scope: MemoryAllocator): NativeAddress {
        val memorySize = (size.toULong() - dataOffset) * Long.SIZE_BYTES.toULong()
        return scope.allocateBuffer(memorySize)
            .also { buffer -> buffer.writeLongs(this, dataOffset) }
            .handler
    }

    private fun IntArray.toBuffer(dataOffset: GPUSize64, scope: MemoryAllocator): NativeAddress {
        val memorySize = (size.toULong() - dataOffset) * Int.SIZE_BYTES.toULong()
        return scope.allocateBuffer(memorySize)
            .also { buffer -> buffer.writeInts(this, dataOffset) }
            .handler
    }

    private fun ShortArray.toBuffer(dataOffset: GPUSize64, scope: MemoryAllocator): NativeAddress {
        val memorySize = (size.toULong() - dataOffset) * Short.SIZE_BYTES.toULong()
        return scope.allocateBuffer(memorySize)
            .also { buffer -> buffer.writeShorts(this, dataOffset) }
            .handler
    }

    private fun ByteArray.toBuffer(dataOffset: GPUSize64, scope: MemoryAllocator): NativeAddress {
        val memorySize = (size.toULong() - dataOffset) * Byte.SIZE_BYTES.toULong()
        return scope.allocateBuffer(memorySize)
            .also { buffer -> buffer.writeBytes(this, dataOffset) }
            .handler
    }
}

actual class ImageBitmapHolder(
    val scop: MemoryAllocator,
    val data: NativeAddress,
    actual val width: UInt,
    actual val height: UInt
) : DrawableHolder, AutoCloseable {

    actual override fun close() {
        scop.close()
    }
}

actual sealed interface DrawableHolder
