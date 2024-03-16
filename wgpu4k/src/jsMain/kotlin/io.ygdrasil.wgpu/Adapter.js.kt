@file:OptIn(ExperimentalStdlibApi::class)

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUAdapter
import io.ygdrasil.wgpu.internal.js.GPURequestAdapterOptions
import kotlinx.coroutines.await

suspend fun requestAdapter(options: GPURequestAdapterOptions? = null): Adapter? {
	// WebGPU device initialization
	if (navigator.gpu == null) {
		println("WebGPU not supported on this browser.")
		return null
	}

	return navigator.gpu.requestAdapter().await()?.let {
		Adapter(it)
	}
}

actual class Adapter(val handler: GPUAdapter) : AutoCloseable {
	override fun close() {
		// Nothing to do on JS
	}

	actual suspend fun requestDevice(): Device? {
		return handler.requestDevice().await()?.let {
			Device(it)
		}
	}
}