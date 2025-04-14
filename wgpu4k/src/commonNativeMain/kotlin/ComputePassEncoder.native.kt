package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUComputePassEncoder
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuComputePassEncoderDispatchWorkgroups
import io.ygdrasil.wgpu.wgpuComputePassEncoderDispatchWorkgroupsIndirect
import io.ygdrasil.wgpu.wgpuComputePassEncoderEnd
import io.ygdrasil.wgpu.wgpuComputePassEncoderPopDebugGroup
import io.ygdrasil.wgpu.wgpuComputePassEncoderPushDebugGroup
import io.ygdrasil.wgpu.wgpuComputePassEncoderSetBindGroup
import io.ygdrasil.wgpu.wgpuComputePassEncoderSetLabel
import io.ygdrasil.wgpu.wgpuComputePassEncoderSetPipeline

actual class ComputePassEncoder(val handler: WGPUComputePassEncoder) : GPUComputePassEncoder {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuComputePassEncoderSetLabel(handler, newLabel)
        }

    actual override fun setPipeline(pipeline: GPUComputePipeline) {
        wgpuComputePassEncoderSetPipeline(handler, (pipeline as ComputePipeline).handler)
    }

    actual override fun dispatchWorkgroups(
        workgroupCountX: GPUSize32,
        workgroupCountY: GPUSize32,
        workgroupCountZ: GPUSize32
    ) {
        wgpuComputePassEncoderDispatchWorkgroups(
            handler,
            workgroupCountX,
            workgroupCountY,
            workgroupCountZ
        )
    }

    actual override fun dispatchWorkgroupsIndirect(indirectBuffer: GPUBuffer, indirectOffset: GPUSize64) {
        wgpuComputePassEncoderDispatchWorkgroupsIndirect(
            handler,
            (indirectBuffer as Buffer).handler,
            indirectOffset
        )
    }

    actual override fun end() {
        wgpuComputePassEncoderEnd(handler)
    }

    actual override fun pushDebugGroup(groupLabel: String) = memoryScope { scope ->
        val groupLabelWGPUStringView = WGPUStringView.allocate(scope)
        scope.map(groupLabel, groupLabelWGPUStringView)
        wgpuComputePassEncoderPushDebugGroup(handler, groupLabelWGPUStringView)
    }

    actual override fun popDebugGroup() {
        wgpuComputePassEncoderPopDebugGroup(handler)
    }

    actual override fun insertDebugMarker(markerLabel: String) = memoryScope { scope ->
        val markerLabelWGPUStringView = WGPUStringView.allocate(scope)
        scope.map(markerLabel, markerLabelWGPUStringView)
        wgpuComputePassEncoderPushDebugGroup(handler, markerLabelWGPUStringView)
    }

    actual override fun setBindGroup(
        index: GPUIndex32,
        bindGroup: GPUBindGroup?,
        dynamicOffsetsData: List<UInt>
    ) = memoryScope { scope ->
        wgpuComputePassEncoderSetBindGroup(handler, index, (bindGroup as BindGroup?)?.handler, dynamicOffsetsData.size.toULong(), scope.map(dynamicOffsetsData))
    }

}