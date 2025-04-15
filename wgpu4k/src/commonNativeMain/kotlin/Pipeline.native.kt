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
import io.ygdrasil.wgpu.wgpuRenderPipelineSetLabel

actual class PipelineLayout(val handler: WGPUPipelineLayout, label: String) : GPUPipelineLayout {

    actual override var label: String = label
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuPipelineLayoutSetLabel(handler, newLabel)
            field = value
        }

    actual override fun close() {
        wgpuPipelineLayoutRelease(handler)
    }
}

actual class RenderPipeline(val handler: WGPURenderPipeline, label: String) : GPURenderPipeline {

    actual override var label: String = label
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuRenderPipelineSetLabel(handler, newLabel)
            field = value
        }

    actual override fun getBindGroupLayout(index: UInt): GPUBindGroupLayout {
        return wgpuRenderPipelineGetBindGroupLayout(handler, index)
            ?.let { BindGroupLayout(it, "") } ?: error("fail to get bindgroup layout")
    }

    actual override fun close() {
        wgpuRenderPipelineRelease(handler)
    }

}
