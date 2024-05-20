package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.*
import java.lang.foreign.MemorySegment

actual class RenderingContext(
	internal val handler: MemorySegment,
	private val sizeProvider: () -> Pair<Int, Int>
) : AutoCloseable {

	val handler2: WGPUSurface = WGPUSurfaceImpl(handler.toPointer())

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
		wgpuSurfaceGetCurrentTexture(handler2, surfaceTexture)
		return Texture(surfaceTexture.texture)
	}

	actual fun present() {
		wgpuSurfacePresent(handler2)
	}

	fun computeSurfaceCapabilities(adapter: Adapter) {
		wgpuSurfaceGetCapabilities(handler2, adapter.handler2, surfaceCapabilities)
	}

	actual fun configure(canvasConfiguration: CanvasConfiguration) {

		if (surfaceCapabilities.formats == null) error("call computeSurfaceCapabilities(adapter: Adapter) before configure")

		wgpuSurfaceConfigure(handler2, canvasConfiguration.convert())
	}

    actual override fun close() {
		wgpuSurfaceRelease(handler2)
	}

	private fun CanvasConfiguration.convert(): WGPUSurfaceConfiguration = WGPUSurfaceConfiguration().also {
		it.device = device.handler
		it.usage = usage
		it.format = format?.value ?: textureFormat.value
		it.presentMode = WGPUPresentMode.WGPUPresentMode_Fifo.value
		it.alphaMode = alphaMode?.value ?: surfaceCapabilities.alphaModes?.getInt(0) ?: error("")
		it.width = width
		it.height = height
	}
}