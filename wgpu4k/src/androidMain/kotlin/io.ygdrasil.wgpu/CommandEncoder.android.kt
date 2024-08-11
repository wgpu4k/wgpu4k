package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class CommandEncoder(internal val handler: Long) : AutoCloseable {

    actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder {
        return JniInterface.instance.wgpuCommandEncoderBeginRenderPass(handler, descriptor)
            .let { RenderPassEncoder(it) }
    }

    actual fun finish(): CommandBuffer {
        return JniInterface.instance.wgpuCommandEncoderFinish(handler)
            .let { CommandBuffer(it) }
    }

    actual fun copyTextureToTexture(
        source: ImageCopyTexture,
        destination: ImageCopyTexture,
        copySize: Size3D
    ) {
        JniInterface.instance.wgpuCommandEncoderCopyTextureToTexture(
            handler,
            source,
            destination,
            copySize
        )
    }

    actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder {
        return JniInterface.instance.wgpuCommandEncoderBeginComputePass(handler, descriptor)
            .let { ComputePassEncoder(it) }
    }

    actual fun copyTextureToBuffer(
        source: ImageCopyTexture,
        destination: ImageCopyBuffer,
        copySize: Size3D,
    ) {

        JniInterface.instance.wgpuCommandEncoderCopyTextureToBuffer(
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

        JniInterface.instance.wgpuCommandEncoderCopyBufferToTexture(
            handler,
            source,
            destination,
            copySize
        )
    }


    actual override fun close() {
        JniInterface.instance.wgpuCommandEncoderRelease(handler)
    }
}