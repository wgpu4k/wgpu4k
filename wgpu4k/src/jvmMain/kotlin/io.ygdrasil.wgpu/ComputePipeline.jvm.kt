package io.ygdrasil.wgpu

import webgpu.WGPUComputePipeline
import webgpu.wgpuComputePipelineGetBindGroupLayout
import webgpu.wgpuComputePipelineRelease

actual class ComputePipeline(internal val handler: WGPUComputePipeline) : AutoCloseable {

    actual fun getBindGroupLayout(index: Int): BindGroupLayout =
        wgpuComputePipelineGetBindGroupLayout(handler, index)
            .let(::BindGroupLayout)

    actual override fun close() {
        wgpuComputePipelineRelease(handler)
    }
}