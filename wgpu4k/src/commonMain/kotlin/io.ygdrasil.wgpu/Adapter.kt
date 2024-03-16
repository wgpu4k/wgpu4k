package io.ygdrasil.wgpu

/**
 * The GPUAdapter interface of the WebGPU API represents a GPU adapter.
 * From this you can request a Device, adapter info, features, and limits.
 */
@OptIn(ExperimentalStdlibApi::class)
expect class Adapter: AutoCloseable {
	suspend fun requestDevice(): Device?
}