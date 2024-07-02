

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUCanvasConfiguration
import io.ygdrasil.wgpu.internal.js.GPUCanvasContext
import io.ygdrasil.wgpu.internal.js.GPUDevice
import org.w3c.dom.HTMLCanvasElement

actual class Surface(private val handler: GPUCanvasContext) : AutoCloseable {

	actual val width: Int
		get() = handler.canvas.width
	actual val height: Int
		get() = handler.canvas.height

	actual val textureFormat: TextureFormat by lazy {
		navigator.gpu
			?.getPreferredCanvasFormat()
			?.let { TextureFormat.of(it) }
			?: error("fail to get canvas prefered format")
	}

	actual fun getCurrentTexture(): Texture {
		return Texture(handler.getCurrentTexture())
	}

	actual override fun close() {
		// Nothing to do on js
	}

	actual fun present() {
		// Nothing to do on js
	}

	actual fun configure(canvasConfiguration: CanvasConfiguration) {
		handler.configure(canvasConfiguration.convert())
	}

	fun CanvasConfiguration.convert(): GPUCanvasConfiguration = object : GPUCanvasConfiguration {
		override var device: GPUDevice = this@convert.device.handler
		override var format: String = this@convert.format?.name ?: textureFormat.actualName
		override var usage: GPUTextureUsageFlags? = this@convert.usage.toFlagInt()
		override var viewFormats: Array<String?>? = this@convert.viewFormats ?: undefined
		override var colorSpace: Any? = this@convert.colorSpace ?: undefined
		override var alphaMode: String? = this@convert.alphaMode?.name ?: undefined
	}
}

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun HTMLCanvasElement.getSurface() = (getContext("webgpu") as? GPUCanvasContext)?.let { Surface(it) }

