package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUPipelineLayout
import io.ygdrasil.webgpu.internal.js.GPURenderPipeline
import io.ygdrasil.webgpu.internal.js.toJsNumber

actual class RenderPipeline(internal val handler: GPURenderPipeline) : AutoCloseable {

    actual fun getBindGroupLayout(index: GPUIndex32): BindGroupLayout = index.toJsNumber()
        .let { handler.getBindGroupLayout(it) }
        .let { BindGroupLayout(it) }

    actual override fun close() {
        // nothing to do here
    }

}

actual class PipelineLayout(internal val handler: GPUPipelineLayout) : AutoCloseable {

    actual override fun close() {
        // nothing to do here
    }

}