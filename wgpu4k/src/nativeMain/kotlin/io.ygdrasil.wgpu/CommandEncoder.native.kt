@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.mapper.map
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import webgpu.native.*

actual class CommandEncoder(internal val handler: WGPUCommandEncoder) : AutoCloseable {

    actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder = memScoped {
        map(descriptor)
            .let { wgpuCommandEncoderBeginRenderPass(handler, it.ptr) }
            ?.let { RenderPassEncoder(it) }
            ?: error("fail to get RenderPassEncoder")
    }


    actual fun finish(): CommandBuffer = memScoped {
        alloc<WGPUCommandBufferDescriptor>()
            .let { wgpuCommandEncoderFinish(handler, it.ptr) }
            ?.let { CommandBuffer(it) }
            ?: error("fail to get CommandBuffer")
    }

    actual fun copyTextureToTexture(
        source: ImageCopyTexture,
        destination: ImageCopyTexture,
        copySize: Size3D
    ) = memScoped {
        wgpuCommandEncoderCopyTextureToTexture(
            handler,
            map(source).ptr,
            map(destination).ptr,
            map(copySize).ptr
        )
    }

    actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder = memScoped {
        descriptor?.let { map(it) }
            .let { wgpuCommandEncoderBeginComputePass(handler, it?.ptr) }
            ?.let { ComputePassEncoder(it) }
            ?: error("fail to get ComputePassEncoder")
    }

    actual fun copyTextureToBuffer(
        source: ImageCopyTexture,
        destination: ImageCopyBuffer,
        copySize: Size3D,
    ) = memScoped {

        wgpuCommandEncoderCopyTextureToBuffer(
            handler,
            map(source).ptr,
            map(destination).ptr,
            map(copySize).ptr
        )
    }

    actual fun copyBufferToTexture(
        source: ImageCopyBuffer,
        destination: ImageCopyTexture,
        copySize: Size3D,
    ) = memScoped {

        wgpuCommandEncoderCopyBufferToTexture(
            handler,
            map(source).ptr,
            map(destination).ptr,
            map(copySize).ptr
        )
    }

    actual override fun close() {
        wgpuCommandEncoderRelease(handler)
    }

}