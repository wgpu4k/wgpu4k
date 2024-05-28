package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import java.lang.foreign.MemorySegment

actual class ComputePipeline(internal val handler: MemorySegment) : AutoCloseable {

    actual fun getBindGroupLayout(index: Int): BindGroupLayout =
        wgpu_h.wgpuComputePipelineGetBindGroupLayout(handler, index)
            .let(::BindGroupLayout)

    actual override fun close() {
        wgpu_h.wgpuComputePipelineRelease(handler)
    }
}