package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class CommandEncoder(internal val handler: Long) : AutoCloseable {

    actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder {
        return JniInterface.wgpuCommandEncoderBeginRenderPass(handler, descriptor)
            .let { RenderPassEncoder(it) }
    }

    actual fun finish(): CommandBuffer {
        return JniInterface.wgpuCommandEncoderFinish(handler)
            .let { CommandBuffer(it) }
    }

    actual fun copyTextureToTexture(
        source: ImageCopyTexture,
        destination: ImageCopyTexture,
        copySize: Size3D
    ) {
        JniInterface.wgpuCommandEncoderCopyTextureToTexture(
            handler,
            source,
            destination,
            copySize
        )
    }

    actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder {
        return JniInterface.wgpuCommandEncoderBeginComputePass(handler, descriptor)
            .let { ComputePassEncoder(it) }
    }

    actual fun copyTextureToBuffer(
        source: ImageCopyTexture,
        destination: ImageCopyBuffer,
        copySize: Size3D,
    ) {

        JniInterface.wgpuCommandEncoderCopyTextureToBuffer(
            handler,
            source,
            destination,
            copySize
        )
    }

    actual fun copyBufferToTexture(
        source: ImageCopyBuffer,
        destination: ImageCopyTexture,
        copySize: Size3D,
    ) {

        JniInterface.wgpuCommandEncoderCopyBufferToTexture(
            handler,
            source,
            destination,
            copySize
        )
    }


    actual override fun close() {
        JniInterface.wgpuCommandEncoderRelease(handler)
    }
}