package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class ComputePipeline(val handler: Long) : AutoCloseable {

    actual fun getBindGroupLayout(index: Int): BindGroupLayout =
        JniInterfaceV2.wgpuComputePipelineGetBindGroupLayout(handler, index)
            .let(::BindGroupLayout)

    actual override fun close() {
        JniInterfaceV2.wgpuComputePipelineRelease(handler)
    }
}