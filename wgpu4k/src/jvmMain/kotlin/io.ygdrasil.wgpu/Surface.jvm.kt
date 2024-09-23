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

	private var _supportedFormats: Set<TextureFormat> = setOf()
	private var _supportedAlphaMode: Set<CompositeAlphaMode> = setOf()

	actual val width: Int
		get() = sizeProvider().first
	actual val height: Int
		get() = sizeProvider().second

	actual val preferredCanvasFormat: TextureFormat? = null
	actual val supportedFormats: Set<TextureFormat>
		get() = _supportedFormats
	actual val supportedAlphaMode: Set<CompositeAlphaMode>
		get() = _supportedAlphaMode

	actual fun getCurrentTexture(): SurfaceTexture = confined { arena ->
		WGPUSurfaceTexture.allocate(arena).let { surfaceTexture ->
			wgpu_h.wgpuSurfaceGetCurrentTexture(handler, surfaceTexture)
			WGPUSurfaceTexture.status(surfaceTexture)
			SurfaceTexture(
				Texture(WGPUSurfaceTexture.texture(surfaceTexture)),
				SurfaceTextureStatus.of(WGPUSurfaceTexture.status(surfaceTexture)) ?: error("fail to get status"),
			)
		}
	}

	actual fun present() {
		wgpu_h.wgpuSurfacePresent(handler)
	}

	fun computeSurfaceCapabilities(adapter: Adapter) = confined { arena ->
		println("computeSurfaceCapabilities")
		val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(arena)
		wgpu_h.wgpuSurfaceGetCapabilities(handler, adapter.handler, surfaceCapabilities)

		val formats = WGPUSurfaceCapabilities.formats(surfaceCapabilities)
		val formatCount = WGPUSurfaceCapabilities.formatCount(surfaceCapabilities)
		_supportedFormats = (0..formatCount.toInt()).map { index ->
			formats.get(ValueLayout.JAVA_INT, index * ValueLayout.JAVA_INT.byteOffset())
				.let { value -> TextureFormat.of(value).also { if (it == null) println("ignoring undefined format with value $value") } }

		}.mapNotNull { it }
			.toSet()

		val alphaModes = WGPUSurfaceCapabilities.alphaModes(surfaceCapabilities)
		val alphaModeCount = WGPUSurfaceCapabilities.alphaModeCount(surfaceCapabilities)
		_supportedAlphaMode = (0..alphaModeCount.toInt()).map { index ->
			alphaModes.get(ValueLayout.JAVA_INT, index * ValueLayout.JAVA_INT.byteOffset())
				.let { value -> CompositeAlphaMode.of(value).also { if (it == null) println("ignoring undefined format with value $value") } }
		}.mapNotNull { it }
			.toSet()


		println("supportedTextureFormats: $supportedFormats")
		println("supportedAlphaMode: $supportedAlphaMode")

		if (_supportedFormats.isEmpty()) {
			println("WARNING: fail to get supported textures on surface, will inject rgba8unorm format")
			_supportedFormats = setOf(TextureFormat.rgba8unorm)
		}

		if (_supportedAlphaMode.isEmpty()) {
			println("WARNING: fail to get supported alpha mode on surface, will inject inherit alpha mode")
			_supportedAlphaMode = setOf(CompositeAlphaMode.inherit)
		}
	}

	actual fun configure(canvasConfiguration: CanvasConfiguration) = confined { arena ->
		wgpu_h.wgpuSurfaceConfigure(handler, arena.map(canvasConfiguration))
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