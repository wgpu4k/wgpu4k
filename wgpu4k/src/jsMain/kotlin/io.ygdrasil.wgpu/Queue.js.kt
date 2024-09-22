package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.*
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Int16Array
import org.khronos.webgl.Int32Array

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

    @JsName("writeBufferI32")
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
                aspect = destination.aspect.stringValue
            },
            image.data.unsafeCast<ArrayBuffer>(),
            createJsObject<GPUImageDataLayout>().apply {
                offset = 0
                bytesPerRow = image.width * bytePerPixel
                rowsPerImage = image.height
            },
            createJsObject<GPUExtent3DDict>().apply {
                width = image.width
                height = image.height
                depthOrArrayLayers = 1
            }
        )
    }
}


actual class ImageBitmapHolder(
    actual val width: Int,
    actual val height: Int,
    val data: ByteArray,
) : DrawableHolder, AutoCloseable {
    actual override fun close() {
        // Nothing to do
    }
}

actual sealed interface DrawableHolder