package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUAdapter
import io.ygdrasil.wgpu.internal.js.GPURequestAdapterOptions
import io.ygdrasil.wgpu.internal.js.navigator
import kotlinx.coroutines.await

actual class Adapter(internal val gpuAdapter: GPUAdapter) : AutoCloseable {
    actual suspend fun requestDevice(): Device? {
        TODO("Not yet implemented")
    }

    actual override fun close() {
    }

}

suspend fun requestAdapter(options: GPURequestAdapterOptions? = null): Adapter? {
    // WebGPU device initialization
    if (navigator.gpu == null) {
        println("WebGPU not supported on this browser.")
        return null
    }

    return navigator.gpu.requestAdapter().await<GPUAdapter>().let {
        Adapter(it)
    }
}