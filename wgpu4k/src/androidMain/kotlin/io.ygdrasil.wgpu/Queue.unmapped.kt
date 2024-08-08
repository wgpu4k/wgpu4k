package io.ygdrasil.wgpu

actual class Queue(internal val handler: Long) {

    actual fun submit(commandsBuffer: List<CommandBuffer>) {
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
actual class ImageBitmapHolder : DrawableHolder {
    actual val width: Int
        get() = TODO("Not yet implemented")
    actual val height: Int
        get() = TODO("Not yet implemented")
}