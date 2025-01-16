package io.ygdrasil.webgpu

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.internal.js.BigInt64Array
import io.ygdrasil.webgpu.internal.js.GPUExtent3DDict
import io.ygdrasil.webgpu.internal.js.GPUImageCopyTexture
import io.ygdrasil.webgpu.internal.js.GPUQueue
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.js.mapJsArray
import io.ygdrasil.webgpu.internal.js.toInt8Array
import io.ygdrasil.webgpu.internal.js.toJsBigInt
import io.ygdrasil.webgpu.internal.js.toJsNumber
import io.ygdrasil.webgpu.internal.web.newGPUImageDataLayout
import io.ygdrasil.webgpu.mapper.map
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Float64Array
import org.khronos.webgl.Int16Array
import org.khronos.webgl.Int32Array
import org.khronos.webgl.Int8Array


actual class Queue(internal val handler: GPUQueue) {

    private val logger = KotlinLogging.logger {}

    actual fun submit(commandsBuffer: List<CommandBuffer>) {
        handler.submit(commandsBuffer.mapJsArray { it.handler })
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
            bufferOffset.toJsNumber(),
            Int16Array(data.mapJsArray { it.toJsNumber() }),
            dataOffset.toJsNumber(),
            size.toJsNumber()
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
            bufferOffset.toJsNumber(),
            Float32Array(data.mapJsArray { it.toJsNumber() }),
            dataOffset.toJsNumber(),
            size.toJsNumber()
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
            bufferOffset.toJsNumber(),
            Int32Array(data.mapJsArray { it.toJsNumber() }),
            dataOffset.toJsNumber(),
            size.toJsNumber()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: ByteArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    )  {
        handler.writeBuffer(
            buffer.handler,
            bufferOffset.toJsNumber(),
            Int8Array(data.mapJsArray { it.toJsNumber() }),
            dataOffset.toJsNumber(),
            size.toJsNumber()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: DoubleArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    )  {
        handler.writeBuffer(
            buffer.handler,
            bufferOffset.toJsNumber(),
            Float64Array(data.mapJsArray { it.toJsNumber() }),
            dataOffset.toJsNumber(),
            size.toJsNumber()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: LongArray,
        dataOffset: GPUSize64,
        size: GPUSize64,
    )  {
        handler.writeBuffer(
            buffer.handler,
            bufferOffset.toJsNumber(),
            BigInt64Array(data.mapJsArray { it.toJsBigInt() }),
            dataOffset.toJsNumber(),
            size.toJsNumber()
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
            Float32Array(data.mapJsArray { it.toJsNumber() }),
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
            Float64Array(data.mapJsArray { it.toJsNumber() }),
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
            Int8Array(data.mapJsArray { it.toJsNumber() }),
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
            Int16Array(data.mapJsArray { it.toJsNumber() }),
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
            Int32Array(data.mapJsArray { it.toJsNumber() }),
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
            BigInt64Array(data.mapJsArray { it.toJsBigInt() }),
            map(dataLayout),
            map(size)
        )
    }

    actual fun copyExternalImageToTexture(
        source: ImageCopyExternalImage,
        destination: ImageCopyTextureTagged,
        copySize: GPUIntegerCoordinates,
    ) {
        logger.trace { "copyExternalImageToTexture" }
        val image = (source.source as? ImageBitmapHolder)
            ?: error("ImageBitmapHolder required as source")

        val bytePerPixel = destination.texture.format.getBytesPerPixel()
        logger.debug { "bytePerPixel: $bytePerPixel" }

        handler.writeTexture(
            createJsObject<GPUImageCopyTexture>().apply {
                texture = destination.texture.handler
                mipLevel = destination.mipLevel
                origin = destination.origin.toArray().mapJsArray { it.toJsNumber() }
                aspect = destination.aspect.value
            },
            image.data.toInt8Array().buffer,
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

actual sealed interface DrawableHolder
actual class ImageBitmapHolder(
    actual val width: GPUSize32,
    actual val height: GPUSize32,
    val data: ByteArray,
) : DrawableHolder, AutoCloseable {

    actual override fun close() {
        // Nothing to do
    }
}