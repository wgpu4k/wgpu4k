package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUComputePipeline
import io.ygdrasil.webgpu.internal.js.toJsNumber

actual class ComputePipeline(internal val handler: GPUComputePipeline) : AutoCloseable {
    actual fun getBindGroupLayout(index: GPUSize32): BindGroupLayout =
        handler.getBindGroupLayout(index.toJsNumber())
            .let { BindGroupLayout(it) }

    actual override fun close() {
        // Nothing to do on JS
    }

}