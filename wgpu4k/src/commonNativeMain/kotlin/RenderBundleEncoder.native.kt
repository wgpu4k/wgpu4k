package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPURenderBundleEncoder
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderDraw
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderDrawIndexed
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderDrawIndexedIndirect
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderDrawIndirect
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderFinish
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderInsertDebugMarker
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderPopDebugGroup
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderPushDebugGroup
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderRelease
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderSetBindGroup
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderSetIndexBuffer
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderSetLabel
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderSetPipeline
import io.ygdrasil.wgpu.wgpuRenderBundleEncoderSetVertexBuffer

actual class RenderBundleEncoder(val handler: WGPURenderBundleEncoder) : GPURenderBundleEncoder {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuRenderBundleEncoderSetLabel(handler, newLabel)
        }

    actual override fun finish(descriptor: GPURenderBundleDescriptor?): GPURenderBundle = memoryScope { scope ->
        descriptor?.let {scope.map(descriptor) }
            .let { wgpuRenderBundleEncoderFinish(handler, it) }
            ?.let(::RenderBundle) ?: error("fail to create render bundle")
    }

    actual override fun setBindGroup(
        index: GPUIndex32,
        bindGroup: GPUBindGroup?,
        dynamicOffsetsData: List<UInt>
    ) = memoryScope { scope ->
        wgpuRenderBundleEncoderSetBindGroup(
            handler,
            index,
            (bindGroup as BindGroup).handler,
            dynamicOffsetsData.size.toULong(),
            scope.map(dynamicOffsetsData)
        )
    }

    actual override fun setPipeline(pipeline: GPURenderPipeline) {
        wgpuRenderBundleEncoderSetPipeline(handler, (pipeline as RenderPipeline).handler)
    }

    actual override fun setVertexBuffer(slot: GPUIndex32, buffer: GPUBuffer?, offset: GPUSize64, size: GPUSize64?) {
        val size = size ?: (buffer?.size?.minus(offset) ?: 0u)
        wgpuRenderBundleEncoderSetVertexBuffer(handler, slot, buffer?.let { (buffer as Buffer).handler }, offset, size)
    }

    actual override fun setIndexBuffer(buffer: GPUBuffer, indexFormat: GPUIndexFormat, offset: GPUSize64, size: GPUSize64?) {
        val size = size ?: buffer.size.minus(offset)
        wgpuRenderBundleEncoderSetIndexBuffer(handler, (buffer as Buffer).handler, indexFormat.value, offset, size)
    }

    actual override fun drawIndexed(
        indexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstIndex: GPUSize32,
        baseVertex: GPUSignedOffset32,
        firstInstance: GPUSize32
    ) {
        wgpuRenderBundleEncoderDrawIndexed(handler, indexCount, instanceCount, firstIndex, baseVertex, firstInstance)
    }

    actual override fun drawIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: GPUSize64
    ) {
        wgpuRenderBundleEncoderDrawIndirect(handler, (indirectBuffer as Buffer).handler, indirectOffset)
    }

    actual override fun drawIndexedIndirect(
        indirectBuffer: GPUBuffer,
        indirectOffset: GPUSize64
    ) {
        wgpuRenderBundleEncoderDrawIndexedIndirect(handler, (indirectBuffer as Buffer).handler, indirectOffset)
    }

    actual override fun draw(
        vertexCount: GPUSize32,
        instanceCount: GPUSize32,
        firstVertex: GPUSize32,
        firstInstance: GPUSize32
    ) {
        wgpuRenderBundleEncoderDraw(handler, vertexCount, instanceCount, firstVertex, firstInstance)
    }

    actual override fun close() {
        wgpuRenderBundleEncoderRelease(handler)
    }

    actual override fun pushDebugGroup(groupLabel: String) = memoryScope { scope ->
        val groupLabelWGPUStringView = WGPUStringView.allocate(scope)
        scope.map(groupLabel, groupLabelWGPUStringView)
        wgpuRenderBundleEncoderPushDebugGroup(handler, groupLabelWGPUStringView)
    }

    actual override fun popDebugGroup() {
        wgpuRenderBundleEncoderPopDebugGroup(handler)
    }

    actual override fun insertDebugMarker(markerLabel: String) = memoryScope { scope ->
        val markerLabelWGPUStringView = WGPUStringView.allocate(scope)
        scope.map(markerLabel, markerLabelWGPUStringView)
        wgpuRenderBundleEncoderInsertDebugMarker(handler, markerLabelWGPUStringView)
    }
}