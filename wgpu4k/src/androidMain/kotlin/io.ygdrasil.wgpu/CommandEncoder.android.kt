package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jna.WGPUCommandBufferDescriptor
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.mapper.map
import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k

actual class CommandEncoder(internal val handler: Long) : AutoCloseable {

    actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder = scoped { arena ->
        arena.map(descriptor)
            .let { NativeWgpu4k.wgpuCommandEncoderBeginRenderPass(handler, it) }
            .let { RenderPassEncoder(it) }
    }

    actual fun finish(): CommandBuffer = scoped { arena ->
        WGPUCommandBufferDescriptor.allocate(arena).pointer.toAddress()
            .let { NativeWgpu4k.wgpuCommandEncoderFinish(handler, it) }
            .let { CommandBuffer(it) }
    }

    actual fun copyTextureToTexture(
        source: ImageCopyTexture,
        destination: ImageCopyTexture,
        copySize: Size3D
    ) = scoped { arena ->
        NativeWgpu4k.wgpuCommandEncoderCopyTextureToTexture(
            handler,
            arena.map(source),
            arena.map(destination),
            arena.map(copySize)
        )
    }

    actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder = scoped { arena ->
        descriptor?.let { arena.map(descriptor) }
            .let { NativeWgpu4k.wgpuCommandEncoderBeginComputePass(handler, it ?: 0L) }
            .let { ComputePassEncoder(it) }
    }

    actual fun copyTextureToBuffer(
        source: ImageCopyTexture,
        destination: ImageCopyBuffer,
        copySize: Size3D,
    ) = scoped { arena ->

        NativeWgpu4k.wgpuCommandEncoderCopyTextureToBuffer(
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

        NativeWgpu4k.wgpuCommandEncoderCopyBufferToTexture(
            handler,
            arena.map(source),
            arena.map(destination),
            arena.map(copySize)
        )
    }


    actual override fun close() {
        NativeWgpu4k.wgpuCommandEncoderRelease(handler)
    }
}