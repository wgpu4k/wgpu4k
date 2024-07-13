package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUComputePipeline

actual class ComputePipeline(internal val handler: GPUComputePipeline) : AutoCloseable {
    actual fun getBindGroupLayout(index: Int): BindGroupLayout =
        handler.getBindGroupLayout(index.toJsNumber())
            .let { BindGroupLayout(it) }

    actual override fun close() {
        // Nothing to do on JS
    }

}