package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.internal.jvm.panama.webgpu_h
import io.ygdrasil.wgpu.mapper.computePassDescriptorMapper
import io.ygdrasil.wgpu.mapper.convert
import io.ygdrasil.wgpu.mapper.map
import java.lang.foreign.MemorySegment

actual class CommandEncoder(internal val handler: MemorySegment) : AutoCloseable {

    actual fun beginRenderPass(descriptor: RenderPassDescriptor): RenderPassEncoder = confined { arena ->
        arena.map(descriptor)
            .let { webgpu_h.wgpuCommandEncoderBeginRenderPass(handler, it) }
            ?.let { RenderPassEncoder(it) }
            ?: error("fail to get RenderPassEncoder")
    }

    actual fun finish(): CommandBuffer =
        WGPUCommandBufferDescriptor()
            .toMemory()
            .let { webgpu_h.wgpuCommandEncoderFinish(handler, it) }
            ?.let { CommandBuffer(it) }
            ?: error("fail to get CommandBuffer")

    actual fun copyTextureToTexture(
        source: ImageCopyTexture,
        destination: ImageCopyTexture,
        copySize: GPUIntegerCoordinates
    ) = confined {
        actualCopyTextureToTexture(
            source.convert().toMemory(),
            destination.convert().toMemory(),
            copySize.convert().toMemory()
        )
    }

    fun actualCopyTextureToTexture(
        source: MemorySegment,
        destination: MemorySegment,
        copySize: MemorySegment
    ) {
        webgpu_h.wgpuCommandEncoderCopyTextureToTexture(
            handler,
            source,
            destination,
            copySize
        )
    }

    actual fun beginComputePass(descriptor: ComputePassDescriptor?): ComputePassEncoder =
        descriptor?.let { computePassDescriptorMapper.map<ComputePassDescriptor, WGPUComputePassDescriptor>(descriptor) }
            .also { it?.write() }?.pointer?.toMemory()
            .let { webgpu_h.wgpuCommandEncoderBeginComputePass(handler, it ?: MemorySegment.NULL) }
            ?.let { ComputePassEncoder(it) }
            ?: error("fail to get ComputePassEncoder")


    actual override fun close() {
        webgpu_h.wgpuCommandEncoderRelease(handler)
    }

}

private fun GPUIntegerCoordinates.convert(): WGPUExtent3D = WGPUExtent3D().also {
    it.height = second
    it.width = first
    it.depthOrArrayLayers = 1
}

