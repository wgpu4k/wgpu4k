package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUCommandEncoder
import io.ygdrasil.wgpu.mapper.map

actual class CommandEncoder(internal val handler: GPUCommandEncoder) : AutoCloseable {
    actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder = map(descriptor)
        .let { handler.beginRenderPass(it) }
        .let(::RenderPassEncoder)

    actual fun finish(): CommandBuffer = handler.finish()
        .let(::CommandBuffer)

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