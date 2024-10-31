package io.ygdrasil.wgpu


import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUCommandBufferDescriptor
import io.ygdrasil.wgpu.mapper.map
import webgpu.WGPUCommandEncoder
import webgpu.wgpuCommandEncoderBeginComputePass
import webgpu.wgpuCommandEncoderBeginRenderPass
import webgpu.wgpuCommandEncoderCopyBufferToTexture
import webgpu.wgpuCommandEncoderCopyTextureToBuffer
import webgpu.wgpuCommandEncoderCopyTextureToTexture
import webgpu.wgpuCommandEncoderFinish
import webgpu.wgpuCommandEncoderRelease
import java.lang.foreign.MemorySegment

actual class CommandEncoder(internal val handler: WGPUCommandEncoder) : AutoCloseable {

    actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder = confined { arena ->
        arena.map(descriptor)
            .let { wgpuCommandEncoderBeginRenderPass(handler, it) }
            ?.let { RenderPassEncoder(it) }
            ?: error("fail to get RenderPassEncoder")
    }

    actual fun finish(): CommandBuffer = confined { arena ->
        WGPUCommandBufferDescriptor.allocate(arena)
            .let { wgpuCommandEncoderFinish(handler, it) }
            ?.let { CommandBuffer(it) }
            ?: error("fail to get CommandBuffer")
    }

    actual fun copyTextureToTexture(
        source: ImageCopyTexture,
        destination: ImageCopyTexture,
        copySize: Size3D
    ) = confined { arena ->
        wgpuCommandEncoderCopyTextureToTexture(
            handler,
            arena.map(source),
            arena.map(destination),
            arena.map(copySize)
        )
    }

    actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder = confined { arena ->
        descriptor?.let { arena.map(descriptor) }
            .let { wgpuCommandEncoderBeginComputePass(handler, it ?: MemorySegment.NULL) }
            ?.let { ComputePassEncoder(it) }
            ?: error("fail to get ComputePassEncoder")
    }

    actual fun copyTextureToBuffer(
        source: ImageCopyTexture,
        destination: ImageCopyBuffer,
        copySize: Size3D,
    ) = confined { arena ->

        wgpuCommandEncoderCopyTextureToBuffer(
            handler,
            arena.map(source),
            arena.map(destination),
            arena.map(copySize)
        )
    }

    actual fun copyBufferToTexture(
        source : ImageCopyBuffer,
        destination: ImageCopyTexture,
        copySize: Size3D,
    ) = confined { arena ->

        wgpuCommandEncoderCopyBufferToTexture(
            handler,
            arena.map(source),
            arena.map(destination),
            arena.map(copySize)
        )
    }

    actual override fun close() {
        wgpuCommandEncoderRelease(handler)
    }
}