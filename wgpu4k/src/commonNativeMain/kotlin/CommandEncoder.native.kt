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

actual class CommandEncoder(val handler: WGPUCommandEncoder) : GPUCommandEncoder {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override fun beginRenderPass(descriptor: GPURenderPassDescriptor): GPURenderPassEncoder = memoryScope { arena ->
        arena.map(descriptor)
            .let { wgpuCommandEncoderBeginRenderPass(handler, it) }
            ?.let { RenderPassEncoder(it) }
            ?: error("fail to get RenderPassEncoder")
    }

    actual override fun finish(descriptor: GPUCommandBufferDescriptor?): GPUCommandBuffer = memoryScope { scope ->
        WGPUCommandBufferDescriptor.allocate(scope)
            .let { wgpuCommandEncoderFinish(handler, it) }
            ?.let { CommandBuffer(it) }
            ?: error("fail to get CommandBuffer")
    }

    actual override fun copyTextureToTexture(
        source: GPUTexelCopyTextureInfo,
        destination: GPUTexelCopyTextureInfo,
        copySize: GPUExtent3D
    ) = memoryScope { scope ->
        wgpuCommandEncoderCopyTextureToTexture(
            handler,
            scope.map(source),
            scope.map(destination),
            scope.map(copySize)
        )
    }

    actual override fun clearBuffer(
        buffer: GPUBuffer,
        offset: GPUSize64,
        size: GPUSize64?
    ) {
        TODO("Not yet implemented")
    }

    actual override fun resolveQuerySet(
        querySet: GPUQuerySet,
        firstQuery: GPUSize32,
        queryCount: GPUSize32,
        destination: GPUBuffer,
        destinationOffset: GPUSize64
    ) {
        TODO("Not yet implemented")
    }

    actual override fun beginComputePass(descriptor: GPUComputePassDescriptor?): GPUComputePassEncoder = memoryScope { scope ->
        descriptor?.let { scope.map(descriptor) }
            .let { wgpuCommandEncoderBeginComputePass(handler, it) }
            ?.let { ComputePassEncoder(it) }
            ?: error("fail to get ComputePassEncoder")
    }

    actual override fun copyBufferToBuffer(
        source: GPUBuffer,
        sourceOffset: GPUSize64,
        destination: GPUBuffer,
        destinationOffset: GPUSize64,
        size: GPUSize64
    ) {
        TODO("Not yet implemented")
    }

    actual override fun copyTextureToBuffer(
        source: GPUTexelCopyTextureInfo,
        destination: GPUTexelCopyBufferInfo,
        copySize: GPUExtent3D
    ) = memoryScope { scope ->

        wgpuCommandEncoderCopyTextureToBuffer(
            handler,
            scope.map(source),
            scope.map(destination),
            scope.map(copySize)
        )
    }

    actual override fun copyBufferToTexture(
        source: GPUTexelCopyBufferInfo,
        destination: GPUTexelCopyTextureInfo,
        copySize: GPUExtent3D
    ) = memoryScope { scope ->

        wgpuCommandEncoderCopyBufferToTexture(
            handler,
            scope.map(source),
            scope.map(destination),
            scope.map(copySize)
        )
    }

    actual override fun pushDebugGroup(groupLabel: String) {
        TODO("Not yet implemented")
    }

    actual override fun popDebugGroup() {
        TODO("Not yet implemented")
    }

    actual override fun insertDebugMarker(markerLabel: String) {
        TODO("Not yet implemented")
    }

    actual override fun close() {
        wgpuCommandEncoderRelease(handler)
    }
}