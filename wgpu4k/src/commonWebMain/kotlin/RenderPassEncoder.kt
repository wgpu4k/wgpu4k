package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map

actual class RenderPassEncoder(private val handler: WGPURenderPassEncoder): GPURenderPassEncoder {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun end() {
        handler.end()
    }

    actual override fun setPipeline(renderPipeline: GPURenderPipeline) {
        handler.setPipeline(renderPipeline.handler)
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

    actual override fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32,
    ) {
        handler.draw(
            vertexCount,
            instanceCount,
            firstVertex,
            firstInstance
        )
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

    actual override fun executeBundles(bundles: List<GPURenderBundle>) {
        handler.executeBundles(bundles.map { it.handler }.toTypedArray())
    }

    actual override fun setViewport(x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float) {
        handler.setViewport(
            x, y, width, height, minDepth, maxDepth
        )
    }

    actual override fun setScissorRect(
        x: GPUIntegerCoordinate,
        y: GPUIntegerCoordinate,
        width: GPUIntegerCoordinate,
        height: GPUIntegerCoordinate,
    ) {
        handler.setScissorRect(x, y, width, height)
    }

    actual override fun setBlendConstant(color: GPUColor) {
        handler.setBlendConstant(map(color))
    }

    actual override fun setStencilReference(reference: GPUStencilValue) {
        handler.setStencilReference(reference)
    }

    actual override fun beginOcclusionQuery(queryIndex: GPUSize32) {
        handler.beginOcclusionQuery(queryIndex)
    }

    actual override fun endOcclusionQuery() {
        handler.endOcclusionQuery()
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

}