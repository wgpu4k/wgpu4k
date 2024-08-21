package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUSurfaceCapabilities
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUSurfaceConfiguration
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUSurfaceTexture
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h.WGPUPresentMode_Fifo
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

actual class Surface(
	internal val handler: MemorySegment,
	private val sizeProvider: () -> Pair<Int, Int>
) : AutoCloseable {

	private lateinit var _textureFormat: TextureFormat
	private lateinit var _alphaMode: CompositeAlphaMode
	actual val width: Int
		get() = sizeProvider().first
	actual val height: Int
		get() = sizeProvider().second

	actual val textureFormat: TextureFormat by lazy {
		if (::_textureFormat.isInitialized.not()) error("call computeSurfaceCapabilities before getting textureFormat")
		_textureFormat
	}

	val alphaMode: CompositeAlphaMode by lazy {
		if (::_alphaMode.isInitialized.not()) error("call computeSurfaceCapabilities before getting textureFormat")
		_alphaMode
	}

	actual fun getCurrentTexture(): Texture = confined { arena ->
		WGPUSurfaceTexture.allocate(arena).let { surfaceTexture ->
			wgpu_h.wgpuSurfaceGetCurrentTexture(handler, surfaceTexture)
			Texture(WGPUSurfaceTexture.texture(surfaceTexture))
		}
	}

	actual fun present() {
		wgpu_h.wgpuSurfacePresent(handler)
	}

	fun computeSurfaceCapabilities(adapter: Adapter) = confined { arena ->
		println("computing surface capabilities")
		val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(arena)
		wgpu_h.wgpuSurfaceGetCapabilities(handler, adapter.handler, surfaceCapabilities)
		if (WGPUSurfaceCapabilities.formatCount(surfaceCapabilities) == 0L) error("fail to get texture format")
		if (WGPUSurfaceCapabilities.alphaModeCount(surfaceCapabilities) == 0L) error("fail to get alpha mode")
		_textureFormat = WGPUSurfaceCapabilities.formats(surfaceCapabilities).get(ValueLayout.JAVA_INT, 0)
			.let { TextureFormat.of(it) ?: error("texture format not found") }
		_alphaMode = WGPUSurfaceCapabilities.alphaModes(surfaceCapabilities).get(ValueLayout.JAVA_INT, 0)
			.let { CompositeAlphaMode.of(it) ?: error("alpha not found") }
	}

	actual fun configure(canvasConfiguration: CanvasConfiguration) = confined { arena ->
		wgpu_h.wgpuSurfaceConfigure(handler, arena.map(canvasConfiguration.copy(alphaMode = this.alphaMode)))
	}

    actual override fun close() {
		wgpu_h.wgpuSurfaceRelease(handler)
	}

	private fun Arena.map(input: CanvasConfiguration): MemorySegment =
		WGPUSurfaceConfiguration.allocate(this).also { output ->
		WGPUSurfaceConfiguration.device(output, input.device.handler)
		WGPUSurfaceConfiguration.usage(output, input.usage.toFlagInt())
		WGPUSurfaceConfiguration.format(output, input.format.value)
		WGPUSurfaceConfiguration.presentMode(output, WGPUPresentMode_Fifo())
		WGPUSurfaceConfiguration.alphaMode(output, input.alphaMode.value)
		WGPUSurfaceConfiguration.width(output, width)
		WGPUSurfaceConfiguration.height(output, height)
	}
}