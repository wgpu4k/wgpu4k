package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUComputePipeline

actual class ComputePipeline(internal val handler: GPUComputePipeline) : AutoCloseable {

    actual fun getBindGroupLayout(index: GPUSize32): BindGroupLayout =
        handler.getBindGroupLayout(index)
            .let { BindGroupLayout(it) }

    actual override fun close() {
        // Nothing to do on js
    }

}