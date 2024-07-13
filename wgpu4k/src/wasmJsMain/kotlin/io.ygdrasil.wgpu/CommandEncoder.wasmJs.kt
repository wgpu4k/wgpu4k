package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUCommandEncoder
import io.ygdrasil.wgpu.internal.js.mapJsArray
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
        handler.copyTextureToTexture(
            map(source),
            map(destination),
            copySize.toArray().mapJsArray { it.toJsNumber() }
        )
    }

    actual fun copyTextureToBuffer(source: ImageCopyTexture, destination: ImageCopyBuffer, copySize: Size3D) {
        handler.copyTextureToBuffer(
            map(source),
            map(destination),
            copySize.toArray().mapJsArray { it.toJsNumber() }
        )
    }

    actual fun copyBufferToTexture(source: ImageCopyBuffer, destination: ImageCopyTexture, copySize: Size3D) {
        handler.copyBufferToTexture(
            map(source),
            map(destination),
            copySize.toArray().mapJsArray { it.toJsNumber() }
        )
    }

    actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder =
        descriptor?.let { map(it) }
            .let { if (it != null) handler.beginComputePass(it) else handler.beginComputePass() }
            .let { ComputePassEncoder(it) }

    actual override fun close() {
        // nothing to do here
    }

}