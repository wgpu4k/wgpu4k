package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class RenderPipeline(internal val handler: Long) : AutoCloseable {

    actual fun getBindGroupLayout(index: Int): BindGroupLayout {
        return JniInterface.instance.wgpuRenderPipelineGetBindGroupLayout(handler, index)
            .let { BindGroupLayout(it) }
    }

    actual override fun close() {
        JniInterface.instance.wgpuRenderPipelineRelease(handler)
    }

}

actual class PipelineLayout(internal val handler: Long)