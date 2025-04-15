package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUCommandBufferDescriptor
import io.ygdrasil.wgpu.WGPUCommandEncoder
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuCommandEncoderBeginComputePass
import io.ygdrasil.wgpu.wgpuCommandEncoderBeginRenderPass
import io.ygdrasil.wgpu.wgpuCommandEncoderClearBuffer
import io.ygdrasil.wgpu.wgpuCommandEncoderCopyBufferToBuffer
import io.ygdrasil.wgpu.wgpuCommandEncoderCopyBufferToTexture
import io.ygdrasil.wgpu.wgpuCommandEncoderCopyTextureToBuffer
import io.ygdrasil.wgpu.wgpuCommandEncoderCopyTextureToTexture
import io.ygdrasil.wgpu.wgpuCommandEncoderFinish
import io.ygdrasil.wgpu.wgpuCommandEncoderInsertDebugMarker
import io.ygdrasil.wgpu.wgpuCommandEncoderPopDebugGroup
import io.ygdrasil.wgpu.wgpuCommandEncoderPushDebugGroup
import io.ygdrasil.wgpu.wgpuCommandEncoderRelease
import io.ygdrasil.wgpu.wgpuCommandEncoderResolveQuerySet
import io.ygdrasil.wgpu.wgpuCommandEncoderSetLabel

actual class CommandEncoder(val handler: WGPUCommandEncoder, label: String) : GPUCommandEncoder {

    actual override var label: String = label
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuCommandEncoderSetLabel(handler, newLabel)
            field = value
        }

    actual override fun beginRenderPass(descriptor: GPURenderPassDescriptor): GPURenderPassEncoder = memoryScope { arena ->
        arena.map(descriptor)
            .let { wgpuCommandEncoderBeginRenderPass(handler, it) }
            ?.let { RenderPassEncoder(it, descriptor.label) }
            ?: error("fail to get RenderPassEncoder")
    }

    actual override fun finish(descriptor: GPUCommandBufferDescriptor?): GPUCommandBuffer = memoryScope { scope ->
        WGPUCommandBufferDescriptor.allocate(scope)
            .let { wgpuCommandEncoderFinish(handler, it) }
            ?.let { CommandBuffer(it, descriptor?.label ?: "") }
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
        val size = size ?: (buffer.size - offset)
        wgpuCommandEncoderClearBuffer(handler, (buffer as Buffer).handler, offset, size)
    }

    actual override fun resolveQuerySet(
        querySet: GPUQuerySet,
        firstQuery: GPUSize32,
        queryCount: GPUSize32,
        destination: GPUBuffer,
        destinationOffset: GPUSize64
    ) {
        wgpuCommandEncoderResolveQuerySet(handler, (querySet as QuerySet).handler, firstQuery, queryCount, (destination as Buffer).handler, destinationOffset)
    }

    actual override fun beginComputePass(descriptor: GPUComputePassDescriptor?): GPUComputePassEncoder = memoryScope { scope ->
        descriptor?.let { scope.map(descriptor) }
            .let { wgpuCommandEncoderBeginComputePass(handler, it) }
            ?.let { ComputePassEncoder(it, descriptor?.label ?: "") }
            ?: error("fail to get ComputePassEncoder")
    }

    actual override fun copyBufferToBuffer(
        source: GPUBuffer,
        sourceOffset: GPUSize64,
        destination: GPUBuffer,
        destinationOffset: GPUSize64,
        size: GPUSize64?
    ) {
        val size = size ?: (source.size - sourceOffset)
        wgpuCommandEncoderCopyBufferToBuffer(handler, (source as Buffer).handler, sourceOffset, (destination as Buffer).handler, destinationOffset, size)
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

    actual override fun pushDebugGroup(groupLabel: String) = memoryScope { scope ->
        val groupLabelWGPUStringView = WGPUStringView.allocate(scope)
        scope.map(groupLabel, groupLabelWGPUStringView)
        wgpuCommandEncoderPushDebugGroup(handler, groupLabelWGPUStringView)
    }

    actual override fun popDebugGroup() {
        wgpuCommandEncoderPopDebugGroup(handler)
    }

    actual override fun insertDebugMarker(markerLabel: String) = memoryScope { scope ->
        val markerLabelWGPUStringView = WGPUStringView.allocate(scope)
        scope.map(markerLabel, markerLabelWGPUStringView)
        wgpuCommandEncoderInsertDebugMarker(handler, markerLabelWGPUStringView)
    }

    actual override fun close() {
        wgpuCommandEncoderRelease(handler)
    }
}