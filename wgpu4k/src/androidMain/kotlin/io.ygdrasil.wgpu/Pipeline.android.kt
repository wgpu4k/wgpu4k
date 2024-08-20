package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class RenderPipeline(val handler: Long) : AutoCloseable {

    actual fun getBindGroupLayout(index: Int): BindGroupLayout {
        return JniInterfaceV2.wgpuRenderPipelineGetBindGroupLayout(handler, index)
            .let { BindGroupLayout(it) }
    }

    actual override fun close() {
        JniInterfaceV2.wgpuRenderPipelineRelease(handler)
    }

}

actual class PipelineLayout(val handler: Long)