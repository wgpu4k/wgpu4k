@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUTexture
import io.ygdrasil.wgpu.internal.js.GPUTextureViewDescriptor

@JsExport
actual class Texture(internal val handler: GPUTexture) : AutoCloseable {

    actual val width: GPUIntegerCoordinateOut
        get() = handler.width
    actual val height: GPUIntegerCoordinateOut
        get() = handler.height
    actual val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = handler.depthOrArrayLayers
    actual val mipLevelCount: GPUIntegerCoordinateOut
        get() = handler.mipLevelCount
    actual val sampleCount: GPUSize32Out
        get() = handler.sampleCount
    actual val dimension: TextureDimension
        get() = TextureDimension.of(handler.dimension) ?: error("unsuported texture dimension $dimension")

    actual val format: TextureFormat
        get() = TextureFormat.of(handler.format) ?: error("unsuported texture format $format")
    actual val usage: GPUFlagsConstant
        get() = handler.usage

	actual fun createView(descriptor: TextureViewDescriptor?): TextureView {
		return TextureView(
			when (descriptor) {
				null -> handler.createView()
				else -> handler.createView(descriptor.convert())
			}
		)
	}

    override fun close() {
        // nothing to do
    }
}

private fun TextureViewDescriptor.convert(): GPUTextureViewDescriptor = object : GPUTextureViewDescriptor {
	override var label: String? = this@convert.label ?: undefined
	override var format: String? = this@convert.format ?: undefined
	override var dimension: String? = this@convert.dimension?.stringValue ?: undefined
	override var aspect: String? = this@convert.aspect ?: undefined
	override var baseMipLevel: GPUIntegerCoordinate? = this@convert.baseMipLevel ?: undefined
	override var mipLevelCount: GPUIntegerCoordinate? = this@convert.mipLevelCount
	override var baseArrayLayer: GPUIntegerCoordinate? = this@convert.baseArrayLayer ?: undefined
	override var arrayLayerCount: GPUIntegerCoordinate? = this@convert.arrayLayerCount
}
