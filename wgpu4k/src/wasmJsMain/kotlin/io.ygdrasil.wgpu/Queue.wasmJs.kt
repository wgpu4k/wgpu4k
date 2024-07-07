package io.ygdrasil.wgpu

actual class Queue {

    actual fun submit(commandsBuffer: Array<CommandBuffer>) {
        TODO("Not yet implemented")
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: FloatArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) {
        TODO("Not yet implemented")
    }

    actual fun writeBuffer(
        buffer: Buffer,
        bufferOffset: GPUSize64,
        data: IntArray,
        dataOffset: GPUSize64,
        size: GPUSize64
    ) {
        TODO("Not yet implemented")
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