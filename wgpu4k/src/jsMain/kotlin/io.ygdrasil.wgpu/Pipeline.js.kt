@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUPipelineLayout
import io.ygdrasil.wgpu.internal.js.GPURenderPipeline

@JsExport
actual class PipelineLayout(internal var handler: GPUPipelineLayout)

@JsExport
actual class RenderPipeline(internal var handler: GPURenderPipeline) : AutoCloseable {

	actual fun getBindGroupLayout(index: Int) = handler
		.getBindGroupLayout(index)
		.let { BindGroupLayout(it) }


	override fun close() {
		// Nothing to do on js
	}

}