package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUPipelineLayout
import io.ygdrasil.wgpu.WGPURenderPipeline
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuPipelineLayoutRelease
import io.ygdrasil.wgpu.wgpuPipelineLayoutSetLabel
import io.ygdrasil.wgpu.wgpuRenderPipelineGetBindGroupLayout
import io.ygdrasil.wgpu.wgpuRenderPipelineRelease

actual class PipelineLayout(val handler: WGPUPipelineLayout) : GPUPipelineLayout {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuPipelineLayoutSetLabel(handler, newLabel)
        }

    actual override fun close() {
        wgpuPipelineLayoutRelease(handler)
    }
}

actual class RenderPipeline(val handler: WGPURenderPipeline) : GPURenderPipeline {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override fun getBindGroupLayout(index: UInt): GPUBindGroupLayout {
        return wgpuRenderPipelineGetBindGroupLayout(handler, index)
            ?.let { BindGroupLayout(it) } ?: error("fail to get bindgroup layout")
    }

    actual override fun close() {
        wgpuRenderPipelineRelease(handler)
    }

}
