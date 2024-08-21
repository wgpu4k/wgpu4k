package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class ComputePipeline(val handler: Long) : AutoCloseable {

    actual fun getBindGroupLayout(index: Int): BindGroupLayout =
        JniInterface.wgpuComputePipelineGetBindGroupLayout(handler, index)
            .let(::BindGroupLayout)

    actual override fun close() {
        JniInterface.wgpuComputePipelineRelease(handler)
    }
}