package io.ygdrasil.webgpu

expect class CommandEncoder : GPUCommandEncoder {

    override var label: String

    override fun beginRenderPass(descriptor: GPURenderPassDescriptor): GPURenderPassEncoder
    override fun beginComputePass(descriptor: GPUComputePassDescriptor?): GPUComputePassEncoder
    override fun copyBufferToBuffer(
        source: GPUBuffer,
        sourceOffset: GPUSize64,
        destination: GPUBuffer,
        destinationOffset: GPUSize64,
        size: GPUSize64
    )

    override fun copyBufferToTexture(
        source: GPUTexelCopyBufferInfo,
        destination: GPUTexelCopyTextureInfo,
        copySize: GPUExtent3D
    )

    override fun copyTextureToBuffer(
        source: GPUTexelCopyTextureInfo,
        destination: GPUTexelCopyBufferInfo,
        copySize: GPUExtent3D
    )

    override fun copyTextureToTexture(
        source: GPUTexelCopyTextureInfo,
        destination: GPUTexelCopyTextureInfo,
        copySize: GPUExtent3D
    )

    override fun clearBuffer(
        buffer: GPUBuffer,
        offset: GPUSize64,
        size: GPUSize64?
    )

    override fun resolveQuerySet(
        querySet: GPUQuerySet,
        firstQuery: GPUSize32,
        queryCount: GPUSize32,
        destination: GPUBuffer,
        destinationOffset: GPUSize64
    )

    override fun finish(descriptor: GPUCommandBufferDescriptor?): GPUCommandBuffer
    override fun pushDebugGroup(groupLabel: String)
    override fun popDebugGroup()
    override fun insertDebugMarker(markerLabel: String)
    override fun close()

}

inline fun GPUCommandEncoder.beginRenderPass(
    descriptor: GPURenderPassDescriptor,
    then: GPURenderPassEncoder.() -> Unit
) {
    beginRenderPass(descriptor).apply(then)
}
