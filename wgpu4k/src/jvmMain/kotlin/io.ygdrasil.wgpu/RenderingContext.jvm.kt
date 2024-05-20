package io.ygdrasil.wgpu


import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUSurfaceCapabilities
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUSurfaceTexture
import io.ygdrasil.wgpu.internal.jvm.panama.webgpu_h
import io.ygdrasil.wgpu.internal.jvm.toMemory
import io.ygdrasil.wgpu.internal.jvm.toPointer
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout

actual class RenderingContext(
	internal val handler: MemorySegment,
	private val sizeProvider: () -> Pair<Int, Int>
) : AutoCloseable {

	val handler2: io.ygdrasil.wgpu.internal.jvm.WGPUSurface = io.ygdrasil.wgpu.internal.jvm.WGPUSurfaceImpl(handler.toPointer())

	private val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(Arena.global())
	actual val width: Int
		get() = sizeProvider().first
	actual val height: Int
		get() = sizeProvider().second

	actual val textureFormat: TextureFormat by lazy {
		WGPUSurfaceCapabilities.formats(surfaceCapabilities).get(ValueLayout.JAVA_INT, 0)
			.let { TextureFormat.of(it) ?: error("texture format not found") }
	}

	actual fun getCurrentTexture(): Texture = confined { arena ->
		WGPUSurfaceTexture.allocate(arena).let { surfaceTexture ->
			webgpu_h.wgpuSurfaceGetCurrentTexture(handler, surfaceTexture)
			Texture(WGPUSurfaceTexture.texture(surfaceTexture))
		}
	}

	actual fun present() {
		webgpu_h.wgpuSurfacePresent(handler)
	}

	fun computeSurfaceCapabilities(adapter: Adapter) {
		webgpu_h.wgpuSurfaceGetCapabilities(handler, adapter.handler, surfaceCapabilities)
	}

	actual fun configure(canvasConfiguration: CanvasConfiguration) {

		if (WGPUSurfaceCapabilities.formats(surfaceCapabilities) == MemorySegment.NULL) error("call computeSurfaceCapabilities(adapter: Adapter) before configure")
		val descriptor = canvasConfiguration.convert()
		descriptor.write()

		webgpu_h.wgpuSurfaceConfigure(handler, descriptor.pointer.toMemory())
	}

    actual override fun close() {
		webgpu_h.wgpuSurfaceRelease(handler)
	}

	private fun CanvasConfiguration.convert(): io.ygdrasil.wgpu.internal.jvm.WGPUSurfaceConfiguration = io.ygdrasil.wgpu.internal.jvm.WGPUSurfaceConfiguration().also {
		it.device = device.handler2
		it.usage = usage
		it.format = format?.value ?: textureFormat.value
		it.presentMode = io.ygdrasil.wgpu.internal.jvm.WGPUPresentMode.WGPUPresentMode_Fifo.value
		it.alphaMode = alphaMode?.value ?: WGPUSurfaceCapabilities.alphaModes(surfaceCapabilities).get(ValueLayout.JAVA_INT, 0)
		it.width = width
		it.height = height
	}
}