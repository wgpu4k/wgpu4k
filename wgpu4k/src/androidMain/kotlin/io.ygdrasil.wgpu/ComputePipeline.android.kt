package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface

actual class ComputePipeline(val handler: Long) : AutoCloseable {

    actual fun getBindGroupLayout(index: Int): BindGroupLayout =
        JnaInterface.wgpuComputePipelineGetBindGroupLayout(handler, index)
            .let(::BindGroupLayout)

    actual override fun close() {
        JnaInterface.wgpuComputePipelineRelease(handler)
    }
}