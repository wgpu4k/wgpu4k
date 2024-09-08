package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k

actual class ComputePipeline(val handler: Long) : AutoCloseable {

    actual fun getBindGroupLayout(index: Int): BindGroupLayout =
        NativeWgpu4k.wgpuComputePipelineGetBindGroupLayout(handler, index)
            .let(::BindGroupLayout)

    actual override fun close() {
        NativeWgpu4k.wgpuComputePipelineRelease(handler)
    }
}