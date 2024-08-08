package io.ygdrasil.wgpu

actual class CommandEncoder(internal val handler: Long) : AutoCloseable {
    actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder {
        TODO("Not yet implemented")
    }

    actual fun finish(): CommandBuffer {
        TODO("Not yet implemented")
    }

    actual fun copyTextureToTexture(
        source: ImageCopyTexture,
        destination: ImageCopyTexture,
        copySize: Size3D,
    ) {
    }

    actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder {
        TODO("Not yet implemented")
    }

    actual fun copyTextureToBuffer(
        source: ImageCopyTexture,
        destination: ImageCopyBuffer,
        copySize: Size3D,
    ) {
    }

    actual fun copyBufferToTexture(
        source: ImageCopyBuffer,
        destination: ImageCopyTexture,
        copySize: Size3D,
    ) {
    }

    actual override fun close() {
    }

}