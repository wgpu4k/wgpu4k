package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*
import io.ygdrasil.wgpu.internal.jvm.panama.webgpu_h
import java.lang.foreign.MemorySegment

actual class PipelineLayout(internal val handler: MemorySegment)

actual class RenderPipeline(internal val handler: MemorySegment) : AutoCloseable {

	val handler2: WGPURenderPipeline = WGPURenderPipelineImpl(handler.toPointer())

	actual fun getBindGroupLayout(index: Int): BindGroupLayout {
		logUnitNative { "wgpuRenderPipelineGetBindGroupLayout" to listOf(handler2, index) }
		return wgpuRenderPipelineGetBindGroupLayout(handler2, index)
			?.let { BindGroupLayout(it) } ?: error("fail to get bindgroup layout")
	}


	actual override fun close() {
		webgpu_h.wgpuRenderPipelineRelease(handler)
	}

}

private fun WGPUBindGroupLayoutImpl.convert(): PipelineLayoutDescriptor.BindGroupLayout {
	TODO("Not yet implemented")
}