package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUPipelineLayout
import io.ygdrasil.wgpu.internal.js.GPURenderPipeline

actual class RenderPipeline(internal val handler: GPURenderPipeline) : AutoCloseable {

    actual fun getBindGroupLayout(index: Int): BindGroupLayout = index.toJsNumber()
        .let { handler.getBindGroupLayout(it) }
        .let { BindGroupLayout(it) }

    actual override fun close() {
        // nothing to do here
    }

}

actual class PipelineLayout(internal val handler: GPUPipelineLayout)