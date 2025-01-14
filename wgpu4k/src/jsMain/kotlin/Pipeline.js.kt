package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUPipelineLayout
import io.ygdrasil.webgpu.internal.js.GPURenderPipeline

actual class PipelineLayout(internal var handler: GPUPipelineLayout) : AutoCloseable {
    actual override fun close() {
        // Nothing to do on js
    }
}

actual class RenderPipeline(internal var handler: GPURenderPipeline) : AutoCloseable {

    actual fun getBindGroupLayout(index: GPUSize32) = handler
        .getBindGroupLayout(index)
        .let { BindGroupLayout(it) }


    actual override fun close() {
        // Nothing to do on js
    }

}