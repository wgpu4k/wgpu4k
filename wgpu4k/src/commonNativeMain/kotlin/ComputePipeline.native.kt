package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUComputePipeline
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuComputePipelineGetBindGroupLayout
import io.ygdrasil.wgpu.wgpuComputePipelineRelease
import io.ygdrasil.wgpu.wgpuComputePipelineSetLabel

actual class ComputePipeline(val handler: WGPUComputePipeline) : GPUComputePipeline {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuComputePipelineSetLabel(handler, newLabel)
        }

    actual override fun getBindGroupLayout(index: UInt): GPUBindGroupLayout =
        (wgpuComputePipelineGetBindGroupLayout(handler, index) ?: error("fail to get bind group layout"))
            .let(::BindGroupLayout)

    actual override fun close() {
        wgpuComputePipelineRelease(handler)
    }
}