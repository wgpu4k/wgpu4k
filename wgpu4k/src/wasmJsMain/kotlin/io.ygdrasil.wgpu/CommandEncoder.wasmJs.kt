package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUCommandEncoder

actual class CommandEncoder(internal val handler: GPUCommandEncoder) : AutoCloseable {
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
        TODO("Not yet implemented")
    }

    actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder {
        TODO("Not yet implemented")
    }

    actual fun copyTextureToBuffer(
        source: ImageCopyTexture,
        destination: ImageCopyBuffer,
        copySize: Size3D,
    ) {
        TODO("Not yet implemented")
    }

    actual fun copyBufferToTexture(
        source: ImageCopyBuffer,
        destination: ImageCopyTexture,
        copySize: Size3D,
    ) {
        TODO("Not yet implemented")
    }

    actual override fun close() {
        // nothing to do here
    }

}