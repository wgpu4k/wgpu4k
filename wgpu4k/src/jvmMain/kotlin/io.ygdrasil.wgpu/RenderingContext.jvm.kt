package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*

actual class RenderingContext(
	internal val handler: WGPUSurface,
	private val sizeProvider: () -> Pair<Int, Int>
) : AutoCloseable {

	private val surfaceCapabilities = WGPUSurfaceCapabilities()
	actual val width: Int
		get() = sizeProvider().first
	actual val height: Int
		get() = sizeProvider().second

	actual val textureFormat: TextureFormat by lazy {
		surfaceCapabilities.formats?.getInt(0)
			?.let { TextureFormat.of(it) ?: error("texture format not found") }
			?: error("call first computeSurfaceCapabilities")
	}

	actual fun getCurrentTexture(): Texture {
		val surfaceTexture = WGPUSurfaceTexture()
		wgpuSurfaceGetCurrentTexture(handler, surfaceTexture)
		return Texture(surfaceTexture.texture)
	}

	actual fun present() {
		wgpuSurfacePresent(handler)
	}

	fun computeSurfaceCapabilities(adapter: Adapter) {
		wgpuSurfaceGetCapabilities(handler, adapter.handler, surfaceCapabilities)
	}

	actual fun configure(canvasConfiguration: CanvasConfiguration) {

		if (surfaceCapabilities.formats == null) error("call computeSurfaceCapabilities(adapter: Adapter) before configure")

		wgpuSurfaceConfigure(handler, canvasConfiguration.convert())
	}

	override fun close() {
		wgpuSurfaceRelease(handler)
	}

	private fun CanvasConfiguration.convert(): WGPUSurfaceConfiguration = WGPUSurfaceConfiguration().also {
		it.device = device.handler
		it.usage = usage ?: WGPUTextureUsage.WGPUTextureUsage_RenderAttachment.value
		it.format = format?.value ?: textureFormat.value
		it.presentMode = WGPUPresentMode.WGPUPresentMode_Fifo.value
		it.alphaMode = alphaMode?.value ?: surfaceCapabilities.alphaModes?.getInt(0) ?: error("")
		it.width = width
		it.height = height
	}
}