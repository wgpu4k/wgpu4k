package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map

actual class CommandEncoder(private val handler: WGPUCommandEncoder) : GPUCommandEncoder {

    actual override var label: String
        get() = handler.label
        set(value) {
            handler.label = value
        }

    actual override fun beginRenderPass(descriptor: GPURenderPassDescriptor): GPURenderPassEncoder = map(descriptor)
        .let(handler::beginRenderPass)
        .let(::RenderPassEncoder)

    actual override fun beginComputePass(descriptor: GPUComputePassDescriptor?): GPUComputePassEncoder =
        when (descriptor) {
            null -> handler.beginComputePass()
            else -> handler.beginComputePass(map(descriptor))
        }.let(::ComputePassEncoder)

    actual override fun copyBufferToBuffer(
        source: GPUBuffer,
        sourceOffset: GPUSize64,
        destination: GPUBuffer,
        destinationOffset: GPUSize64,
        size: GPUSize64
    ) {
        TODO("Not yet implemented")
    }

    actual override fun copyBufferToTexture(
        source: GPUTexelCopyBufferInfo,
        destination: GPUTexelCopyTextureInfo,
        copySize: GPUExtent3D
    ) {
        TODO("Not yet implemented")
    }

    actual override fun copyTextureToBuffer(
        source: GPUTexelCopyTextureInfo,
        destination: GPUTexelCopyBufferInfo,
        copySize: GPUExtent3D
    ) {
        TODO("Not yet implemented")
    }

    actual override fun copyTextureToTexture(
        source: GPUTexelCopyTextureInfo,
        destination: GPUTexelCopyTextureInfo,
        copySize: GPUExtent3D
    ) {
        TODO("Not yet implemented")
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

    actual override fun finish(descriptor: GPUCommandBufferDescriptor?): GPUCommandBuffer = when (descriptor) {
        null -> handler.finish()
        else -> handler.finish(map(descriptor))
    }.let(::CommandBuffer)

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
        // Nothing to do
    }
}
