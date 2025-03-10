package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUComputePipeline
import io.ygdrasil.wgpu.wgpuComputePipelineGetBindGroupLayout
import io.ygdrasil.wgpu.wgpuComputePipelineRelease

actual class ComputePipeline(internal val handler: WGPUComputePipeline) : GPUComputePipeline {

    override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual fun getBindGroupLayout(index: UInt): BindGroupLayout =
        (wgpuComputePipelineGetBindGroupLayout(handler, index) ?: error("fail to get bind group layout"))
            .let(::BindGroupLayout)

    actual override fun close() {
        wgpuComputePipelineRelease(handler)
    }
}