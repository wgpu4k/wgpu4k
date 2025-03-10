package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUPipelineLayout
import io.ygdrasil.wgpu.WGPURenderPipeline
import io.ygdrasil.wgpu.wgpuPipelineLayoutRelease
import io.ygdrasil.wgpu.wgpuRenderPipelineGetBindGroupLayout
import io.ygdrasil.wgpu.wgpuRenderPipelineRelease

actual class PipelineLayout(internal val handler: WGPUPipelineLayout) : GPUPipelineLayout {

    override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override fun close() {
        wgpuPipelineLayoutRelease(handler)
    }
}

actual class RenderPipeline(internal val handler: WGPURenderPipeline) : GPURenderPipeline {

    override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual fun getBindGroupLayout(index: UInt): BindGroupLayout {
        return wgpuRenderPipelineGetBindGroupLayout(handler, index)
            ?.let { BindGroupLayout(it) } ?: error("fail to get bindgroup layout")
    }

    actual override fun close() {
        wgpuRenderPipelineRelease(handler)
    }

}
