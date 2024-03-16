package io.ygdrasil.wgpu.examples

import io.ygdrasil.libsdl.*
import io.ygdrasil.wgpu.internal.jvm.wgpuGetVersion
import kotlinx.coroutines.runBlocking

fun main() {
	printVersion()
	initSDL()
	runBlocking {
		jvmApplication()
	}
}

fun initSDL() {
	if (SDL_Init(SDL_INIT_EVERYTHING.toInt()) != 0) {
		error("SDL_Init Error: ${SDL_GetError()}")
	}
}

fun printVersion() {
	println("WGPU version ${wgpuGetVersion()}")
	SDL_version().apply {
		SDL_GetVersion(this)
		println("SDL version $major.$minor.$patch")
	}
}