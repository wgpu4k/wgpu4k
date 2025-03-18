package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map

actual class RenderBundleEncoder(
    internal val handler: WGPURenderBundleEncoder,
) : GPURenderBundleEncoder {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun finish(descriptor: GPURenderBundleDescriptor?): GPURenderBundle =
        map(descriptor)
            .let { handler.finish(it) }
            .let(::RenderBundle)


    actual override fun setPipeline(renderPipeline: GPURenderPipeline) {
        handler.setPipeline((renderPipeline as RenderPipeline).handler)
    }

    override fun setIndexBuffer(
        buffer: GPUBuffer,
        indexFormat: GPUIndexFormat,
        offset: GPUSize64,
        size: GPUSize64?
    ) {
        TODO("Not yet implemented")
    }

    override fun setVertexBuffer(
        slot: GPUIndex32,
        buffer: GPUBuffer?,
        offset: GPUSize64,
        size: GPUSize64?
    ) {
        TODO("Not yet implemented")
    }

    actual override fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32,
    ) {
        handler.drawIndexed(indexCount, instanceCount, firstIndex, baseVertex, firstInstance)
    }

    override fun drawIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: GPUSize64
    ) {
        TODO("Not yet implemented")
    }

    override fun drawIndexedIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: GPUSize64
    ) {
        TODO("Not yet implemented")
    }

    actual override fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32,
    ) {
        handler.draw(vertexCount, instanceCount, firstVertex, firstInstance)
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

    override fun setBindGroup(
        index: GPUIndex32,
        bindGroup: GPUBindGroup?,
        dynamicOffsetsData: List<UInt>
    ) {
        TODO("Not yet implemented")
    }

    actual override fun close() {
        // Nothing to do on js
    }

}

