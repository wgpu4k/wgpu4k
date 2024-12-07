package io.ygdrasil.wgpu

import ffi.ArrayHolder
import ffi.MemoryAllocator
import ffi.MemoryBuffer
import ffi.memoryScope
import webgpu.WGPUCompositeAlphaMode
import webgpu.WGPUSurface
import webgpu.WGPUSurfaceCapabilities
import webgpu.WGPUSurfaceConfiguration
import webgpu.WGPUSurfaceTexture
import webgpu.WGPUTextureFormat
import webgpu.wgpuSurfaceConfigure
import webgpu.wgpuSurfaceGetCapabilities
import webgpu.wgpuSurfaceGetCurrentTexture
import webgpu.wgpuSurfacePresent
import webgpu.wgpuSurfaceRelease

actual class Surface(
	internal val handler: WGPUSurface
) : AutoCloseable {

	private var _supportedFormats: Set<TextureFormat> = setOf()
	private var _supportedAlphaMode: Set<CompositeAlphaMode> = setOf()
	private var _width: UInt? = null
	private var _height: UInt? = null

	actual val width: UInt
		get() = _width ?: error("width not yet initialized, call configure() first")
	actual val height: UInt
		get() = _height ?: error("height not yet initialized, call configure() first")

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

		val formats = surfaceCapabilities.formats ?: error("fail to get formats")
		_supportedFormats = surfaceCapabilities.toTextureFormats(formats)

		val alphaModes = surfaceCapabilities.alphaModes ?: error("fail to get alpha modes")
		_supportedAlphaMode = surfaceCapabilities.toAlphaMode(alphaModes)

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

	private fun WGPUSurfaceCapabilities.toTextureFormats(
		formats: ArrayHolder<WGPUTextureFormat>
	) = UIntArray(formatCount.toInt()) { 0u }
		.also {
			MemoryBuffer(formats.handler, formatCount * Int.SIZE_BYTES.toULong())
				.readUInts(it)
		}
		.map {
			TextureFormat.of(it.toInt())
				.also { if (it == null) println("ignoring undefined format with value $it") }
		}
		.filterNotNull()
		.toSet()

	private fun WGPUSurfaceCapabilities.toAlphaMode(
		alphaModes: ArrayHolder<WGPUCompositeAlphaMode>
	) = UIntArray(formatCount.toInt()) { 0u }
		.also {
			MemoryBuffer(alphaModes.handler, formatCount * Int.SIZE_BYTES.toULong())
				.readUInts(it)
		}
		.map {
			CompositeAlphaMode.of(it.toInt())
				.also { if (it == null) println("ignoring undefined alpha mode with value $it") }
		}
		.filterNotNull()
		.toSet()

	actual fun configure(surfaceConfiguration: SurfaceConfiguration) = memoryScope { scope ->
		_width = surfaceConfiguration.width
		_height = surfaceConfiguration.height
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