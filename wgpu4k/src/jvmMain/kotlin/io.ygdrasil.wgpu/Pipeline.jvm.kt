package io.ygdrasil.wgpu

import webgpu.WGPUPipelineLayout
import webgpu.WGPURenderPipeline
import webgpu.wgpuPipelineLayoutRelease
import webgpu.wgpuRenderPipelineGetBindGroupLayout
import webgpu.wgpuRenderPipelineRelease

actual class PipelineLayout(internal val handler: WGPUPipelineLayout) : AutoCloseable {
	actual override fun close() {
		wgpuPipelineLayoutRelease(handler)
	}
}

actual class RenderPipeline(internal val handler: WGPURenderPipeline) : AutoCloseable {

	actual fun getBindGroupLayout(index: UInt): BindGroupLayout {
		return wgpuRenderPipelineGetBindGroupLayout(handler, index)
			?.let { BindGroupLayout(it) } ?: error("fail to get bindgroup layout")
	}

	actual override fun close() {
		wgpuRenderPipelineRelease(handler)
	}

}
