package io.ygdrasil.webgpu

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.internal.js.GPUAdapter
import io.ygdrasil.webgpu.internal.js.GPURequestAdapterOptions
import io.ygdrasil.webgpu.mapper.map
import kotlinx.coroutines.await

private val logger = KotlinLogging.logger {}

suspend fun requestAdapter(options: GPURequestAdapterOptions? = null): Adapter? {
    // WebGPU device initialization
    if (navigator.gpu == null) {
        logger.error { "WebGPU not supported in this browser." }
        return null
    }

    return navigator.gpu.requestAdapter().await()?.let {
        Adapter(it)
    }
}

actual class Adapter(val handler: GPUAdapter) : AutoCloseable {

    actual val features: Set<FeatureName> by lazy {
        handler.features
            .map { FeatureName.of(it) ?: error("Unsupported feature $it") }
            .toSet()
    }

    actual val limits: Limits by lazy { map(handler.limits) }

    actual suspend fun requestDevice(descriptor: DeviceDescriptor): Device? {
        return map(descriptor)
            .let { handler.requestDevice(it) }
            .await()
            .let(::Device)
    }

    actual override fun close() {
        // Nothing to do on JS
    }
}