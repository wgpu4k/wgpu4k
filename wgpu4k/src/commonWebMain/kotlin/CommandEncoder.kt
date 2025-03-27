package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map

actual class CommandEncoder(val handler: WGPUCommandEncoder) : GPUCommandEncoder {

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
        handler.copyBufferToBuffer(
            (source as Buffer).handler,
            sourceOffset.asJsNumber(),
            (destination as Buffer).handler,
            destinationOffset.asJsNumber(),
            size.asJsNumber()
        )
    }

    actual override fun copyBufferToTexture(
        source: GPUTexelCopyBufferInfo,
        destination: GPUTexelCopyTextureInfo,
        copySize: GPUExtent3D
    ) {
        handler.copyBufferToTexture(
            map(source),
            map(destination),
            map(copySize)
        )
    }

    actual override fun copyTextureToBuffer(
        source: GPUTexelCopyTextureInfo,
        destination: GPUTexelCopyBufferInfo,
        copySize: GPUExtent3D
    ) {
        handler.copyTextureToBuffer(
            map(source),
            map(destination),
            map(copySize)
        )
    }

    actual override fun copyTextureToTexture(
        source: GPUTexelCopyTextureInfo,
        destination: GPUTexelCopyTextureInfo,
        copySize: GPUExtent3D
    ) {
        handler.copyTextureToTexture(
            map(source),
            map(destination),
            map(copySize)
        )
    }

    actual override fun clearBuffer(
        buffer: GPUBuffer,
        offset: GPUSize64,
        size: GPUSize64?
    ) = when (size) {
        null -> handler.clearBuffer(
            (buffer as Buffer).handler,
            offset.asJsNumber()
        )
        else -> handler.clearBuffer(
            (buffer as Buffer).handler,
            offset.asJsNumber(),
            size.asJsNumber()
        )
    }

    actual override fun resolveQuerySet(
        querySet: GPUQuerySet,
        firstQuery: GPUSize32,
        queryCount: GPUSize32,
        destination: GPUBuffer,
        destinationOffset: GPUSize64
    ) {
        handler.resolveQuerySet(
            (querySet as QuerySet).handler,
            firstQuery.asJsNumber(),
            queryCount.asJsNumber(),
            (destination as Buffer).handler,
            destinationOffset.asJsNumber()
        )
    }

    actual override fun finish(descriptor: GPUCommandBufferDescriptor?): GPUCommandBuffer = when (descriptor) {
        null -> handler.finish()
        else -> handler.finish(map(descriptor))
    }.let(::CommandBuffer)

    actual override fun pushDebugGroup(groupLabel: String) {
        handler.pushDebugGroup(groupLabel)
    }

    actual override fun popDebugGroup() {
        handler.popDebugGroup()
    }

    actual override fun insertDebugMarker(markerLabel: String) {
        handler.insertDebugMarker(markerLabel)
    }

    actual override fun close() {
        // Nothing to do
    }
}
