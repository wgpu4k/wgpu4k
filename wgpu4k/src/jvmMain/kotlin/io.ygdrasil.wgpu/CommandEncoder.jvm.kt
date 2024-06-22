package io.ygdrasil.wgpu


import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUCommandBufferDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import io.ygdrasil.wgpu.mapper.map
import java.lang.foreign.MemorySegment

actual class CommandEncoder(internal val handler: MemorySegment) : AutoCloseable {

    actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder = confined { arena ->
        arena.map(descriptor)
            .let { wgpu_h.wgpuCommandEncoderBeginRenderPass(handler, it) }
            ?.let { RenderPassEncoder(it) }
            ?: error("fail to get RenderPassEncoder")
    }

    actual fun finish(): CommandBuffer = confined { arena ->
        WGPUCommandBufferDescriptor.allocate(arena)
            .let { wgpu_h.wgpuCommandEncoderFinish(handler, it) }
            ?.let { CommandBuffer(it) }
            ?: error("fail to get CommandBuffer")
    }

    actual fun copyTextureToTexture(
        source: ImageCopyTexture,
        destination: ImageCopyTexture,
        copySize: Size3D
    ) = confined { arena ->
        wgpu_h.wgpuCommandEncoderCopyTextureToTexture(
            handler,
            arena.map(source),
            arena.map(destination),
            arena.map(copySize)
        )
    }

    actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder = confined { arena ->
        descriptor?.let { arena.map(descriptor) }
            .let { wgpu_h.wgpuCommandEncoderBeginComputePass(handler, it ?: MemorySegment.NULL) }
            ?.let { ComputePassEncoder(it) }
            ?: error("fail to get ComputePassEncoder")
    }

    actual fun copyTextureToBuffer(
        source: ImageCopyTexture,
        destination: ImageCopyBuffer,
        copySize: Size3D,
    ) = confined { arena ->

        wgpu_h.wgpuCommandEncoderCopyTextureToBuffer(
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

        wgpu_h.wgpuCommandEncoderCopyBufferToTexture(
            handler,
            arena.map(source),
            arena.map(destination),
            arena.map(copySize)
        )
    }


    actual override fun close() {
        wgpu_h.wgpuCommandEncoderRelease(handler)
    }



}