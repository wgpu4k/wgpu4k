package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.BigInt64Array
import io.ygdrasil.webgpu.internal.js.GPUExtent3DDict
import io.ygdrasil.webgpu.internal.js.GPUImageCopyTexture
import io.ygdrasil.webgpu.internal.js.GPUQueue
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.web.newGPUImageDataLayout
import io.ygdrasil.webgpu.mapper.map
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Float64Array
import org.khronos.webgl.Int16Array
import org.khronos.webgl.Int32Array
import org.khronos.webgl.Int8Array

actual class Queue(internal val handler: GPUQueue) {
    actual fun submit(commandsBuffer: List<CommandBuffer>) {
        handler.submit(commandsBuffer.map { it.handler }.toTypedArray())
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: ShortArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) {
        handler.writeBuffer(
            buffer.handler,
            bufferOffset,
            data.unsafeCast<Int16Array>(),
            dataOffset,
            size
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) {
        handler.writeBuffer(
            buffer.handler,
            bufferOffset,
            data.unsafeCast<Float32Array>(),
            dataOffset,
            size
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: IntArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) {
        handler.writeBuffer(
            buffer.handler,
            bufferOffset,
            data.unsafeCast<Int32Array>(),
            dataOffset,
            size
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: ByteArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) {
        handler.writeBuffer(
            buffer.handler,
            bufferOffset,
            data.unsafeCast<Int8Array>(),
            dataOffset,
            size
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: DoubleArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) {
        handler.writeBuffer(
            buffer.handler,
            bufferOffset,
            data.unsafeCast<Float64Array>(),
            dataOffset,
            size
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: LongArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    ) {
        handler.writeBuffer(
            buffer.handler,
            bufferOffset,
            data.unsafeCast<BigInt64Array>(),
            dataOffset,
            size
        )
    }

    actual fun writeTexture(
        destination: ImageCopyTexture,
        data: FloatArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    ) {
        handler.writeTexture(
            map(destination),
            data.unsafeCast<Float32Array>(),
            map(dataLayout),
            map(size)
        )
    }

    actual fun writeTexture(
        destination: ImageCopyTexture,
        data: DoubleArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    ) {
        handler.writeTexture(
            map(destination),
            data.unsafeCast<Float64Array>(),
            map(dataLayout),
            map(size)
        )
    }

    actual fun writeTexture(
        destination: ImageCopyTexture,
        data: ByteArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    ) {
        handler.writeTexture(
            map(destination),
            data.unsafeCast<Int8Array>(),
            map(dataLayout),
            map(size)
        )
    }

    actual fun writeTexture(
        destination: ImageCopyTexture,
        data: ShortArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    ) {
        handler.writeTexture(
            map(destination),
            data.unsafeCast<Int16Array>(),
            map(dataLayout),
            map(size)
        )
    }

    actual fun writeTexture(
        destination: ImageCopyTexture,
        data: IntArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    ) {
        handler.writeTexture(
            map(destination),
            data.unsafeCast<Int32Array>(),
            map(dataLayout),
            map(size)
        )
    }

    actual fun writeTexture(
        destination: ImageCopyTexture,
        data: LongArray,
        dataLayout: TextureDataLayout,
        size: Size3D,
    ) {
        handler.writeTexture(
            map(destination),
            data.unsafeCast<BigInt64Array>(),
            map(dataLayout),
            map(size)
        )
    }

    actual fun copyExternalImageToTexture(
        source: ImageCopyExternalImage,
        destination: ImageCopyTextureTagged,
        copySize: GPUIntegerCoordinates,
    ) {

        val image = (source.source as? ImageBitmapHolder)
            ?: error("ImageBitmapHolder required as source")

        val bytePerPixel = destination.texture.format.getBytesPerPixel()

        handler.writeTexture(
            createJsObject<GPUImageCopyTexture>().apply {
                texture = destination.texture.handler
                mipLevel = destination.mipLevel
                origin = destination.origin.toArray()
                aspect = destination.aspect.value
            },
            image.data.unsafeCast<ArrayBuffer>(),
            newGPUImageDataLayout(
                offset = 0uL,
                bytesPerRow = image.width * bytePerPixel,
                rowsPerImage = image.height
            ),
            createJsObject<GPUExtent3DDict>().apply {
                width = image.width
                height = image.height
                depthOrArrayLayers = 1u
            }
        )
    }
}


actual class ImageBitmapHolder(
    actual val width: GPUSize32,
    actual val height: GPUSize32,
    val data: ByteArray,
) : DrawableHolder, AutoCloseable {
    actual override fun close() {
        // Nothing to do
    }
}

actual sealed interface DrawableHolder
