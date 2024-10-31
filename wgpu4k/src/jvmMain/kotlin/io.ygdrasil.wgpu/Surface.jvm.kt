package io.ygdrasil.wgpu

import ffi.MemoryAllocator
import ffi.memoryScope
import webgpu.WGPUSurface
import webgpu.WGPUSurfaceCapabilities
import webgpu.WGPUSurfaceConfiguration
import webgpu.WGPUSurfaceTexture
import webgpu.wgpuSurfaceConfigure
import webgpu.wgpuSurfaceGetCapabilities
import webgpu.wgpuSurfaceGetCurrentTexture
import webgpu.wgpuSurfacePresent
import webgpu.wgpuSurfaceRelease
import java.lang.foreign.ValueLayout

actual class Surface(
	internal val handler: WGPUSurface,
	private val sizeProvider: () -> Pair<UInt, UInt>
) : AutoCloseable {

	private var _supportedFormats: Set<TextureFormat> = setOf()
	private var _supportedAlphaMode: Set<CompositeAlphaMode> = setOf()

	actual val width: UInt
		get() = sizeProvider().first
	actual val height: UInt
		get() = sizeProvider().second

	actual val preferredCanvasFormat: TextureFormat? = null
	actual val supportedFormats: Set<TextureFormat>
		get() = _supportedFormats
	actual val supportedAlphaMode: Set<CompositeAlphaMode>
		get() = _supportedAlphaMode

	actual fun getCurrentTexture(): SurfaceTexture = memoryScope { scope ->
		WGPUSurfaceTexture.allocate(scope).let { surfaceTexture ->
			wgpuSurfaceGetCurrentTexture(handler, surfaceTexture)
			surfaceTexture.status
			SurfaceTexture(
				Texture(surfaceTexture.texture ?: error("fail to get texture from surface")),
				SurfaceTextureStatus.of(surfaceTexture.status) ?: error("fail to get status"),
			)
		}
	}

	actual fun present() {
		wgpuSurfacePresent(handler)
	}

	fun computeSurfaceCapabilities(adapter: Adapter) = memoryScope { scope ->
		println("computeSurfaceCapabilities")
		val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(scope)
		wgpuSurfaceGetCapabilities(handler, adapter.handler, surfaceCapabilities)

		val formats = surfaceCapabilities.formats
		val formatCount = surfaceCapabilities.formatCount
		_supportedFormats = (0..formatCount.toInt()).map { index ->
			formats.get(ValueLayout.JAVA_INT, index * ValueLayout.JAVA_INT.byteOffset())
				.let { value -> TextureFormat.of(value).also { if (it == null) println("ignoring undefined format with value $value") } }

		}.mapNotNull { it }
			.toSet()

		val alphaModes = surfaceCapabilities.alphaModes
		val alphaModeCount = surfaceCapabilities.alphaModeCount
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

	actual fun configure(surfaceConfiguration: SurfaceConfiguration) = memoryScope { scope ->
		wgpuSurfaceConfigure(handler, scope.map(surfaceConfiguration))
	}

    actual override fun close() {
		wgpuSurfaceRelease(handler)
	}

	private fun MemoryAllocator.map(input: SurfaceConfiguration): WGPUSurfaceConfiguration =
		WGPUSurfaceConfiguration.allocate(this).also { output ->
			output.device = input.device.handler
			output.usage = input.usage.toFlagULong()
			output.format = input.format.uValue
			output.presentMode = input.presentMode.uValue
			output.alphaMode = input.alphaMode.uValue
			output.width = width
			output.height = height
	}
}