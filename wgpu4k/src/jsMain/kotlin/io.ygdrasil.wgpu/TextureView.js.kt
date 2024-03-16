@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUTextureView

@JsExport
actual class TextureView(internal val handler: GPUTextureView) : AutoCloseable {

	override fun close() {
		// Nothing to do
	}
}