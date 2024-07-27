package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import java.lang.foreign.MemorySegment

actual class PipelineLayout(internal val handler: MemorySegment)

actual class RenderPipeline(internal val handler: MemorySegment) : AutoCloseable {

	actual fun getBindGroupLayout(index: Int): BindGroupLayout {
		return wgpu_h.wgpuRenderPipelineGetBindGroupLayout(handler, index)
			?.let { BindGroupLayout(it) } ?: error("fail to get bindgroup layout")
	}

	actual override fun close() {
		wgpu_h.wgpuRenderPipelineRelease(handler)
	}

}
