package io.ygdrasil.webgpu

import webgpu.WGPUComputePipeline
import webgpu.wgpuComputePipelineGetBindGroupLayout
import webgpu.wgpuComputePipelineRelease

actual class ComputePipeline(internal val handler: WGPUComputePipeline) : AutoCloseable {

    actual fun getBindGroupLayout(index: UInt): BindGroupLayout =
        (wgpuComputePipelineGetBindGroupLayout(handler, index) ?: error("fail to get bind group layout"))
            .let(::BindGroupLayout)

    actual override fun close() {
        wgpuComputePipelineRelease(handler)
    }
}