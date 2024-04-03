package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*

actual class PipelineLayout(internal val handler: WGPUPipelineLayout)

actual class RenderPipeline(internal val handler: WGPURenderPipeline) : AutoCloseable {

	actual fun getBindGroupLayout(index: Int): BindGroupLayout {
		logUnitNative { "wgpuRenderPipelineGetBindGroupLayout" to listOf(handler, index) }
		return wgpuRenderPipelineGetBindGroupLayout(handler, index)
			?.let { BindGroupLayout(it) } ?: error("fail to get bindgroup layout")
	}


	override fun close() {
		logUnitNative { "wgpuRenderPipelineRelease" to listOf(handler) }
		wgpuRenderPipelineRelease(handler)
	}

}

private fun WGPUBindGroupLayoutImpl.convert(): PipelineLayoutDescriptor.BindGroupLayout {
	TODO("Not yet implemented")
}