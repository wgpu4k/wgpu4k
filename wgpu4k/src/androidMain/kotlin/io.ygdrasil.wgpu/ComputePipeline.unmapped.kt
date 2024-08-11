package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class ComputePipeline(internal val handler: Long) : AutoCloseable {

    actual fun getBindGroupLayout(index: Int): BindGroupLayout =
        JniInterface.instance.wgpuComputePipelineGetBindGroupLayout(handler, index)
            .let(::BindGroupLayout)

    actual override fun close() {
        JniInterface.instance.wgpuComputePipelineRelease(handler)
    }
}