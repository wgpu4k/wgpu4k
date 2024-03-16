@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUBindGroup

@JsExport
actual class BindGroup(internal val handler: GPUBindGroup) : AutoCloseable {
	override fun close() {
		// Nothing to do on js
	}
}