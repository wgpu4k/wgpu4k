package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUPipelineLayout
import io.ygdrasil.wgpu.internal.js.GPURenderPipeline

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