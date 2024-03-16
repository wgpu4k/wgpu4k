@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

import kotlin.js.JsExport

expect class RenderingContext: AutoCloseable {

	val width: Int
	val height: Int
	val textureFormat: TextureFormat

	fun getCurrentTexture(): Texture

	/**
	 * Schedule this texture to be presented on the owning surface.
	 *
	 * Needs to be called after any work on the texture is scheduled via Queue::submit.
	 *
	 * Platform dependent behavior
	 * On Wayland, present will attach a wl_buffer to the underlying wl_surface and commit the new surface state. If it is desired to do things such as request a frame callback, scale the surface using the viewporter or synchronize other double buffered state, then these operations should be done before the call to present.
	 */
	fun present()

	fun configure(canvasConfiguration: CanvasConfiguration)
}

@JsExport
data class CanvasConfiguration(
	var device: Device,
	var format: TextureFormat? = null,
	var usage: GPUTextureUsageFlags? = null,
	var viewFormats: Array<String?>? = null,
	var colorSpace: Any? = null,
	var alphaMode: CompositeAlphaMode? = null
)