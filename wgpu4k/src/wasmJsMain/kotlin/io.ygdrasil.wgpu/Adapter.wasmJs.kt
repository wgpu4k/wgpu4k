package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUAdapter
import io.ygdrasil.webgpu.internal.js.GPUDevice
import io.ygdrasil.webgpu.internal.js.GPURequestAdapterOptions
import io.ygdrasil.webgpu.internal.js.navigator
import io.ygdrasil.webgpu.mapper.map
import kotlinx.coroutines.await

actual class Adapter(internal val handler: GPUAdapter) : AutoCloseable {

    actual val features: Set<FeatureName> by lazy {
        (0..handler.features.length)
            .map {  index ->
                index.let { handler.features[it].toString() }
                    .let { FeatureName.of(it) ?: error("Unsupported feature $it") }
            }
            .toSet()
    }

    actual val limits: Limits by lazy { map(handler.limits) }

    actual suspend fun requestDevice(descriptor: DeviceDescriptor): Device? {
        return handler.requestDevice()
            .await<GPUDevice>()
            .let { Device(it) }
    }

    actual override fun close() {
        // Nothing to do on JS
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