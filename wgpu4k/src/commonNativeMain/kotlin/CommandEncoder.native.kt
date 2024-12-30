package io.ygdrasil.webgpu


import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import webgpu.WGPUCommandBufferDescriptor
import webgpu.WGPUCommandEncoder
import webgpu.wgpuCommandEncoderBeginComputePass
import webgpu.wgpuCommandEncoderBeginRenderPass
import webgpu.wgpuCommandEncoderCopyBufferToTexture
import webgpu.wgpuCommandEncoderCopyTextureToBuffer
import webgpu.wgpuCommandEncoderCopyTextureToTexture
import webgpu.wgpuCommandEncoderFinish
import webgpu.wgpuCommandEncoderRelease

actual class CommandEncoder(internal val handler: WGPUCommandEncoder) : AutoCloseable {

    actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder = memoryScope { arena ->
        arena.map(descriptor)
            .let { wgpuCommandEncoderBeginRenderPass(handler, it) }
            ?.let { RenderPassEncoder(it) }
            ?: error("fail to get RenderPassEncoder")
    }

    actual fun finish(): CommandBuffer = memoryScope { scope ->
        WGPUCommandBufferDescriptor.allocate(scope)
            .let { wgpuCommandEncoderFinish(handler, it) }
            ?.let { CommandBuffer(it) }
            ?: error("fail to get CommandBuffer")
    }

    actual fun copyTextureToTexture(
        source: ImageCopyTexture,
        destination: ImageCopyTexture,
        copySize: Size3D
    ) = memoryScope { scope ->
        wgpuCommandEncoderCopyTextureToTexture(
            handler,
            scope.map(source),
            scope.map(destination),
            scope.map(copySize)
        )
    }

    actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder = memoryScope { scope ->
        descriptor?.let { scope.map(descriptor) }
            .let { wgpuCommandEncoderBeginComputePass(handler, it) }
            ?.let { ComputePassEncoder(it) }
            ?: error("fail to get ComputePassEncoder")
    }

    actual fun copyTextureToBuffer(
        source: ImageCopyTexture,
        destination: ImageCopyBuffer,
        copySize: Size3D,
    ) = memoryScope { scope ->

        wgpuCommandEncoderCopyTextureToBuffer(
            handler,
            scope.map(source),
            scope.map(destination),
            scope.map(copySize)
        )
    }

    actual fun copyBufferToTexture(
        source: ImageCopyBuffer,
        destination: ImageCopyTexture,
        copySize: Size3D,
    ) = memoryScope { scope ->

        wgpuCommandEncoderCopyBufferToTexture(
            handler,
            scope.map(source),
            scope.map(destination),
            scope.map(copySize)
        )
    }

    actual override fun close() {
        wgpuCommandEncoderRelease(handler)
    }
}