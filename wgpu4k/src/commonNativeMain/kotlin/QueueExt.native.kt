package io.ygdrasil.webgpu

import ffi.MemoryAllocator
import ffi.NativeAddress
import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUExtent3D
import io.ygdrasil.wgpu.WGPUOrigin3D
import io.ygdrasil.wgpu.WGPUTexelCopyBufferLayout
import io.ygdrasil.wgpu.WGPUTexelCopyTextureInfo
import io.ygdrasil.wgpu.wgpuQueueWriteBuffer
import io.ygdrasil.wgpu.wgpuQueueWriteTexture


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

actual fun GPUQueue.copyExternalImageToTexture(
    source: ImageCopyExternalImage,
    destination: ImageCopyTextureTagged,
    copySize: GPUExtent3D
) = memoryScope { scope ->

    val image = (source.source as? ImageBitmapHolder)
        ?: error("ImageBitmapHolder required as source")

    val bytePerPixel = destination.texture.format.getBytesPerPixel()

    wgpuQueueWriteTexture(
        (this as Queue).handler,
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

}

internal fun MemoryAllocator.map(input: ImageCopyTextureTagged) =
    WGPUTexelCopyTextureInfo.allocate(this).also { output ->
        output.texture = (input.texture as Texture).handler
        output.mipLevel = input.mipLevel
        map(input.origin, output.origin)
        output.aspect = input.aspect.value
    }

internal fun map(input: GPUOrigin3D?, output: WGPUOrigin3D) {
    output.x = input?.x ?: 0u
    output.y = input?.y ?: 0u
    output.z = input?.z ?: 0u
}


actual fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: ShortArray,
    dataOffset: GPUSize64,
    size: GPUSize64
) = memoryScope { scope ->
    wgpuQueueWriteBuffer(
        (this as Queue).handler,
        (buffer as Buffer).handler,
        bufferOffset,
        data.toBuffer(dataOffset, scope),
        size * Short.SIZE_BYTES.toULong()
    )
}

actual fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: FloatArray,
    dataOffset: GPUSize64,
    size: GPUSize64
) = memoryScope { scope ->
    wgpuQueueWriteBuffer(
        (this as Queue).handler,
        (buffer as Buffer).handler,
        bufferOffset,
        data.toBuffer(dataOffset, scope),
        size * Float.SIZE_BYTES.toULong()
    )
}

actual fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: IntArray,
    dataOffset: GPUSize64,
    size: GPUSize64
) = memoryScope { scope ->
    wgpuQueueWriteBuffer(
        (this as Queue).handler,
        (buffer as Buffer).handler,
        bufferOffset,
        data.toBuffer(dataOffset, scope),
        size * Float.SIZE_BYTES.toULong()
    )
}

actual fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: ByteArray,
    dataOffset: GPUSize64,
    size: GPUSize64,
) = memoryScope { scope ->
    wgpuQueueWriteBuffer(
        (this as Queue).handler,
        (buffer as Buffer).handler,
        bufferOffset,
        data.toBuffer(dataOffset, scope),
        size * Byte.SIZE_BYTES.toULong()
    )
}

actual fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: DoubleArray,
    dataOffset: GPUSize64,
    size: GPUSize64,
) = memoryScope { scope ->
    wgpuQueueWriteBuffer(
        (this as Queue).handler,
        (buffer as Buffer).handler,
        bufferOffset,
        data.toBuffer(dataOffset, scope),
        size * Double.SIZE_BYTES.toULong()
    )
}

actual fun GPUQueue.writeBuffer(
    buffer: GPUBuffer,
    bufferOffset: GPUSize64,
    data: LongArray,
    dataOffset: GPUSize64,
    size: GPUSize64,
) = memoryScope { scope ->
    wgpuQueueWriteBuffer(
        (this as Queue).handler,
        (buffer as Buffer).handler,
        bufferOffset,
        data.toBuffer(dataOffset, scope),
        size * Long.SIZE_BYTES.toULong()
    )
}

actual fun GPUQueue.writeTexture(
    destination: GPUTexelCopyTextureInfo,
    data: FloatArray,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D,
) = memoryScope { scope ->
    wgpuQueueWriteTexture(
        (this as Queue).handler,
        scope.map(destination),
        data.toBuffer(0u, scope),
        (Float.SIZE_BYTES * data.size).toULong(),
        scope.map(dataLayout),
        scope.map(size)
    )
}

actual fun GPUQueue.writeTexture(
    destination: GPUTexelCopyTextureInfo,
    data: DoubleArray,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D,
) = memoryScope { scope ->
    wgpuQueueWriteTexture(
        (this as Queue).handler,
        scope.map(destination),
        data.toBuffer(0u, scope),
        (Double.SIZE_BYTES * data.size).toULong(),
        scope.map(dataLayout),
        scope.map(size)
    )
}

actual fun GPUQueue.writeTexture(
    destination: GPUTexelCopyTextureInfo,
    data: ByteArray,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D,
) = memoryScope { scope ->
    wgpuQueueWriteTexture(
        (this as Queue).handler,
        scope.map(destination),
        data.toBuffer(0u, scope),
        (Byte.SIZE_BYTES * data.size).toULong(),
        scope.map(dataLayout),
        scope.map(size)
    )
}

actual fun GPUQueue.writeTexture(
    destination: GPUTexelCopyTextureInfo,
    data: ShortArray,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D,
) = memoryScope { scope ->
    wgpuQueueWriteTexture(
        (this as Queue).handler,
        scope.map(destination),
        data.toBuffer(0u, scope),
        (Short.SIZE_BYTES * data.size).toULong(),
        scope.map(dataLayout),
        scope.map(size)
    )
}

actual fun GPUQueue.writeTexture(
    destination: GPUTexelCopyTextureInfo,
    data: IntArray,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D,
) = memoryScope { scope ->
    wgpuQueueWriteTexture(
        (this as Queue).handler,
        scope.map(destination),
        data.toBuffer(0u, scope),
        (Int.SIZE_BYTES * data.size).toULong(),
        scope.map(dataLayout),
        scope.map(size)
    )
}

actual fun GPUQueue.writeTexture(
    destination: GPUTexelCopyTextureInfo,
    data: LongArray,
    dataLayout: GPUTexelCopyBufferLayout,
    size: GPUExtent3D,
) = memoryScope { scope ->
    wgpuQueueWriteTexture(
        (this as Queue).handler,
        scope.map(destination),
        data.toBuffer(0u, scope),
        (Long.SIZE_BYTES * data.size).toULong(),
        scope.map(dataLayout),
        scope.map(size)
    )
}

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