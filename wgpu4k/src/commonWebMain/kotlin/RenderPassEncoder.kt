package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map

actual class RenderPassEncoder(private val handler: WGPURenderPassEncoder): GPURenderPassEncoder {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun end() {
        handler.end()
    }

    actual override fun setPipeline(pipeline: GPURenderPipeline) {
        handler.setPipeline((pipeline as RenderPipeline).handler)
    }

    actual override fun setIndexBuffer(
        buffer: GPUBuffer,
        indexFormat: GPUIndexFormat,
        offset: GPUSize64,
        size: GPUSize64?
    ) = when (size) {
        null -> handler.setIndexBuffer(
            (buffer as Buffer).handler,
            indexFormat.value,
            offset.asJsNumber()
        )
        else -> handler.setIndexBuffer(
            (buffer as Buffer).handler,
            indexFormat.value,
            offset.asJsNumber(),
            size.asJsNumber()
        )
    }

    actual override fun setVertexBuffer(
        slot: GPUIndex32,
        buffer: GPUBuffer?,
        offset: GPUSize64,
        size: GPUSize64?
    ) = when (size) {
        null -> handler.setVertexBuffer(
            slot.asJsNumber(),
            (buffer as Buffer).handler,
            offset.asJsNumber()
        )
        else -> handler.setVertexBuffer(
            slot.asJsNumber(),
            (buffer as Buffer).handler,
            offset.asJsNumber(),
            size.asJsNumber()
        )
    }

    actual override fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32,
    ) {
        handler.draw(
            vertexCount.asJsNumber(),
            instanceCount.asJsNumber(),
            firstVertex.asJsNumber(),
            firstInstance.asJsNumber()
        )
    }

    actual override fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32,
    ) {
        handler.drawIndexed(
            indexCount.asJsNumber(),
            instanceCount.asJsNumber(),
            firstIndex.asJsNumber(),
            baseVertex.asJsNumber(),
            firstInstance.asJsNumber()
        )
    }

    actual override fun drawIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: GPUSize64
    ) {
        handler.drawIndirect((indirectBuffer as Buffer).handler, indirectOffset.asJsNumber())
    }

    actual override fun drawIndexedIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: GPUSize64
    ) {
        handler.drawIndexedIndirect((indirectBuffer as Buffer).handler, indirectOffset.asJsNumber())
    }

    actual override fun executeBundles(bundles: List<GPURenderBundle>) {
        handler.executeBundles(bundles.mapJsArray { (it as RenderBundle).handler })
    }

    actual override fun setViewport(x: Float, y: Float, width: Float, height: Float, minDepth: Float, maxDepth: Float) {
        handler.setViewport(
            x.asJsNumber(),
            y.asJsNumber(),
            width.asJsNumber(),
            height.asJsNumber(),
            minDepth.asJsNumber(),
            maxDepth.asJsNumber()
        )
    }

    actual override fun setScissorRect(
        x: GPUIntegerCoordinate,
        y: GPUIntegerCoordinate,
        width: GPUIntegerCoordinate,
        height: GPUIntegerCoordinate,
    ) {
        handler.setScissorRect(
            x.asJsNumber(),
            y.asJsNumber(),
            width.asJsNumber(),
            height.asJsNumber()
        )
    }

    actual override fun setBlendConstant(color: GPUColor) {
        handler.setBlendConstant(map(color))
    }

    actual override fun setStencilReference(reference: GPUStencilValue) {
        handler.setStencilReference(reference.asJsNumber())
    }

    actual override fun beginOcclusionQuery(queryIndex: GPUSize32) {
        handler.beginOcclusionQuery(queryIndex.asJsNumber())
    }

    actual override fun endOcclusionQuery() {
        handler.endOcclusionQuery()
    }

    actual override fun pushDebugGroup(groupLabel: String) {
        handler.pushDebugGroup(groupLabel)
    }

    actual override fun popDebugGroup() {
        handler.popDebugGroup()
    }

    actual override fun insertDebugMarker(markerLabel: String) {
        handler.insertDebugMarker(markerLabel)
    }

    actual override fun setBindGroup(
        index: GPUIndex32,
        bindGroup: GPUBindGroup?,
        dynamicOffsetsData: List<UInt>
    ) {
        handler.setBindGroup(
            index.asJsNumber(),
            (bindGroup as BindGroup).handler,
            map(dynamicOffsetsData)
        )
    }

}