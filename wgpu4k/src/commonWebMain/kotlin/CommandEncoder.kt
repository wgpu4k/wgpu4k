package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map

actual class CommandEncoder(private val handler: WGPUCommandEncoder) : GPUCommandEncoder {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun beginRenderPass(descriptor: GPURenderPassDescriptor): GPURenderPassEncoder = map(descriptor)
        .let { handler.beginRenderPass(it) }
        .let(::RenderPassEncoder)

    actual override fun beginComputePass(descriptor: GPUComputePassDescriptor?): GPUComputePassEncoder =
        descriptor?.let { map(it) }
            .let { handler.beginComputePass(it) }
            .let { ComputePassEncoder(it) }

    override fun copyBufferToBuffer(
        source: GPUBuffer,
        sourceOffset: GPUSize64,
        destination: GPUBuffer,
        destinationOffset: GPUSize64,
        size: GPUSize64
    ) {
        TODO("Not yet implemented")
    }

    override fun copyBufferToTexture(
        source: GPUTexelCopyBufferInfo,
        destination: GPUTexelCopyTextureInfo,
        copySize: GPUExtent3D
    ) {
        TODO("Not yet implemented")
    }

    override fun copyTextureToBuffer(
        source: GPUTexelCopyTextureInfo,
        destination: GPUTexelCopyBufferInfo,
        copySize: GPUExtent3D
    ) {
        TODO("Not yet implemented")
    }

    override fun copyTextureToTexture(
        source: GPUTexelCopyTextureInfo,
        destination: GPUTexelCopyTextureInfo,
        copySize: GPUExtent3D
    ) {
        TODO("Not yet implemented")
    }

    override fun clearBuffer(
        buffer: GPUBuffer,
        offset: GPUSize64,
        size: GPUSize64?
    ) {
        TODO("Not yet implemented")
    }

    override fun resolveQuerySet(
        querySet: GPUQuerySet,
        firstQuery: GPUSize32,
        queryCount: GPUSize32,
        destination: GPUBuffer,
        destinationOffset: GPUSize64
    ) {
        TODO("Not yet implemented")
    }

    override fun finish(descriptor: GPUCommandBufferDescriptor?): GPUCommandBuffer {
        TODO("Not yet implemented")
    }

    actual override fun close() {
        // Nothing to do
    }
    override fun pushDebugGroup(groupLabel: String) {
        TODO("Not yet implemented")
    }

    override fun popDebugGroup() {
        TODO("Not yet implemented")
    }

    override fun insertDebugMarker(markerLabel: String) {
        TODO("Not yet implemented")
    }
}
