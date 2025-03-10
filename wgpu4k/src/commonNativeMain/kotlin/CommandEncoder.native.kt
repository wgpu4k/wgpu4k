package io.ygdrasil.webgpu


import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUCommandBufferDescriptor
import io.ygdrasil.wgpu.WGPUCommandEncoder
import io.ygdrasil.wgpu.wgpuCommandEncoderBeginComputePass
import io.ygdrasil.wgpu.wgpuCommandEncoderBeginRenderPass
import io.ygdrasil.wgpu.wgpuCommandEncoderCopyBufferToTexture
import io.ygdrasil.wgpu.wgpuCommandEncoderCopyTextureToBuffer
import io.ygdrasil.wgpu.wgpuCommandEncoderCopyTextureToTexture
import io.ygdrasil.wgpu.wgpuCommandEncoderFinish
import io.ygdrasil.wgpu.wgpuCommandEncoderRelease

actual class CommandEncoder(internal val handler: WGPUCommandEncoder) : GPUCommandEncoder {

    actual fun beginRenderPass(descriptor: GPURenderPassDescriptor): RenderPassEncoder = memoryScope { arena ->
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
        source: GPUImageCopyTexture,
        destination: GPUImageCopyTexture,
        copySize: Size3D
    ) = memoryScope { scope ->
        wgpuCommandEncoderCopyTextureToTexture(
            handler,
            scope.map(source),
            scope.map(destination),
            scope.map(copySize)
        )
    }

    actual fun beginComputePass(descriptor: GPUComputePassDescriptor?): ComputePassEncoder = memoryScope { scope ->
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