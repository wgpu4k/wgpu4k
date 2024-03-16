@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUTexture
import io.ygdrasil.wgpu.internal.js.GPUTextureViewDescriptor

@JsExport
actual class Texture(internal val handler: GPUTexture) : AutoCloseable {
	override fun close() {
		// nothing to do
	}

	actual fun createView(descriptor: TextureViewDescriptor?): TextureView {
		return TextureView(
			when (descriptor) {
				null -> handler.createView()
				else -> handler.createView(descriptor.convert())
			}
		)
	}
}

private fun TextureViewDescriptor.convert(): GPUTextureViewDescriptor = object : GPUTextureViewDescriptor {
	override var label: String? = this@convert.label ?: undefined
	override var format: String? = this@convert.format ?: undefined
	override var dimension: String? = this@convert.dimension ?: undefined
	override var aspect: String? = this@convert.aspect ?: undefined
	override var baseMipLevel: GPUIntegerCoordinate? = this@convert.baseMipLevel ?: undefined
	override var mipLevelCount: GPUIntegerCoordinate? = this@convert.mipLevelCount ?: undefined
	override var baseArrayLayer: GPUIntegerCoordinate? = this@convert.baseArrayLayer ?: undefined
	override var arrayLayerCount: GPUIntegerCoordinate? = this@convert.baseArrayLayer ?: undefined
}
