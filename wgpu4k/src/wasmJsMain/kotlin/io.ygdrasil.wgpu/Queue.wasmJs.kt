package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUQueue
import io.ygdrasil.wgpu.internal.js.toJsArray
import io.ygdrasil.wgpu.internal.js.toJsNumber
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Int32Array

actual class Queue(internal val handler: GPUQueue) {

    actual fun submit(commandsBuffer: List<CommandBuffer>) {
        handler.submit(commandsBuffer.map { it.handler }.toJsArray())
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) {
        handler.writeBuffer(
            buffer.handler,
            bufferOffset.toJsNumber(),
            Float32Array(data.map { it.toJsNumber() }.toJsArray()),
            dataOffset.toJsNumber(),
            size.toJsNumber()
        )
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: IntArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) {
        handler.writeBuffer(
            buffer.handler,
            bufferOffset.toJsNumber(),
            Int32Array(data.map { it.toJsNumber() }.toJsArray()),
            dataOffset.toJsNumber(),
            size.toJsNumber()
        )
    }

    actual fun copyExternalImageToTexture(
        source: ImageCopyExternalImage,
        destination: ImageCopyTextureTagged,
        copySize: GPUIntegerCoordinates
    ) {
        TODO("Not yet implemented")
    }

}

actual sealed interface DrawableHolder
actual class ImageBitmapHolder(
    actual val width: Int,
    actual val height: Int,
    val data: ByteArray
) : DrawableHolder