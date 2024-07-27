@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.WGPUComputePipeline
import webgpu.wgpuComputePipelineGetBindGroupLayout
import webgpu.wgpuComputePipelineRelease

actual class ComputePipeline(internal val handler: WGPUComputePipeline) : AutoCloseable {

    actual fun getBindGroupLayout(index: Int): BindGroupLayout =
        wgpuComputePipelineGetBindGroupLayout(handler, index.toUInt())
            ?.let(::BindGroupLayout) ?: error("fail to get BindGroupLayout")

    actual override fun close() {
        wgpuComputePipelineRelease(handler)
    }
}