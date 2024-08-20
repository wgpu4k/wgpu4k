package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class CommandEncoder(internal val handler: Long) : AutoCloseable {

    actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder {
        return JniInterfaceV2.wgpuCommandEncoderBeginRenderPass(handler, descriptor)
            .let { RenderPassEncoder(it) }
    }

    actual fun finish(): CommandBuffer {
        return JniInterfaceV2.wgpuCommandEncoderFinish(handler)
            .let { CommandBuffer(it) }
    }

    actual fun copyTextureToTexture(
        source: ImageCopyTexture,
        destination: ImageCopyTexture,
        copySize: Size3D
    ) {
        JniInterfaceV2.wgpuCommandEncoderCopyTextureToTexture(
            handler,
            source,
            destination,
            copySize
        )
    }

    actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder {
        return JniInterfaceV2.wgpuCommandEncoderBeginComputePass(handler, descriptor)
            .let { ComputePassEncoder(it) }
    }

    actual fun copyTextureToBuffer(
        source: ImageCopyTexture,
        destination: ImageCopyBuffer,
        copySize: Size3D,
    ) {

        JniInterfaceV2.wgpuCommandEncoderCopyTextureToBuffer(
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

        JniInterfaceV2.wgpuCommandEncoderCopyBufferToTexture(
            handler,
            source,
            destination,
            copySize
        )
    }


    actual override fun close() {
        JniInterfaceV2.wgpuCommandEncoderRelease(handler)
    }
}