package io.ygdrasil.wgpu.examples

import com.sun.jna.ptr.IntByReference
import io.ygdrasil.libsdl.*
import io.ygdrasil.wgpu.RenderingContext
import io.ygdrasil.wgpu.WGPU

suspend fun jvmApplication() = (WGPU.createInstance() ?: error("fail to wgpu instance")).use { instance ->

	val window = SDL_CreateWindow(
		"", SDL_WINDOWPOS_CENTERED.toInt(),
		SDL_WINDOWPOS_CENTERED.toInt(), 800, 600,
		SDL_WindowFlags.SDL_WINDOW_SHOWN.value
	) ?: error("fail to create window ${SDL_GetError()}")

	val surface = instance.getSurface(window) ?: error("fail to create surface")
	val renderingContext = RenderingContext(surface) {
		val width = IntByReference()
		val height = IntByReference()
		SDL_GetWindowSize(window, width.pointer, height.pointer)
		width.value to height.value
	}

	val adapter = instance.requestAdapter(renderingContext)
		?: error("fail to get adapter")

	val device = adapter.requestDevice()
		?: error("fail to get device")

	renderingContext.computeSurfaceCapabilities(adapter)

	val application = object : Application(
		renderingContext,
		device,
		adapter,
		assetManager
	) {
		override fun run() {
			while (true) {
				renderFrame()
				pollEvent()
			}
		}

		private fun pollEvent() {
			val event = SDL_Event()
			while (SDL_PollEvent(event) != 0) {
			}
		}


	}

	application.run()

}


