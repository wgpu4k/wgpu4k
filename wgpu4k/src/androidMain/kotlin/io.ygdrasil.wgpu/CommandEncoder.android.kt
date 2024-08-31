package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface
import io.ygdrasil.wgpu.internal.JniInterface
import io.ygdrasil.wgpu.internal.jna.WGPUCommandBufferDescriptor
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.mapper.map
import java.lang.foreign.MemorySegment

actual class CommandEncoder(internal val handler: Long) : AutoCloseable {

    actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder = scoped { arena ->
        arena.map(descriptor)
            .let { JnaInterface.wgpuCommandEncoderBeginRenderPass(handler, it) }
            .let { RenderPassEncoder(it) }
    }

    actual fun finish(): CommandBuffer = scoped { arena ->
        WGPUCommandBufferDescriptor.allocate(arena).pointer.toAddress()
            .let { JnaInterface.wgpuCommandEncoderFinish(handler, it) }
            .let { CommandBuffer(it) }
    }

    actual fun copyTextureToTexture(
        source: ImageCopyTexture,
        destination: ImageCopyTexture,
        copySize: Size3D
    ) = scoped { arena ->
        JnaInterface.wgpuCommandEncoderCopyTextureToTexture(
            handler,
            arena.map(source),
            arena.map(destination),
            arena.map(copySize)
        )
    }

    actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder = scoped { arena ->
        descriptor?.let { arena.map(descriptor) }
            .let { JnaInterface.wgpuCommandEncoderBeginComputePass(handler, it ?: 0L) }
            .let { ComputePassEncoder(it) }
    }

    actual fun copyTextureToBuffer(
        source: ImageCopyTexture,
        destination: ImageCopyBuffer,
        copySize: Size3D,
    ) = scoped { arena ->

        JnaInterface.wgpuCommandEncoderCopyTextureToBuffer(
            handler,
            arena.map(source),
            arena.map(destination),
            arena.map(copySize)
        )
    }

    actual fun copyBufferToTexture(
        source: ImageCopyBuffer,
        destination: ImageCopyTexture,
        copySize: Size3D,
    ) = scoped { arena ->

        JnaInterface.wgpuCommandEncoderCopyBufferToTexture(
            handler,
            arena.map(source),
            arena.map(destination),
            arena.map(copySize)
        )
    }


    actual override fun close() {
        JnaInterface.wgpuCommandEncoderRelease(handler)
    }
}