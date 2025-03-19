package io.ygdrasil.webgpu

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.mapper.map

private val logger = KotlinLogging.logger {}

suspend fun requestAdapter(options: GPURequestAdapterOptions? = null): Adapter? {
    // WebGPU device initialization
    if (navigator.gpu == null) {
        logger.error { "WebGPU not supported in this browser." }
        return null
    }

    return navigator.gpu.requestAdapter().wait<WGPUAdapter>()?.let {
        Adapter(it)
    }
}

actual class Adapter(val handler: WGPUAdapter) : GPUAdapter {

    actual override val features: Set<GPUFeatureName> by lazy {
        handler.features
            .map { GPUFeatureName.of(it) ?: error("Unsupported feature $it") }
            .toSet()
    }

    actual override val limits: GPUSupportedLimits by lazy { map(handler.limits) }

    override val info: GPUAdapterInfo
        get() = TODO("Not yet implemented")
    override val isFallbackAdapter: Boolean
        get() = TODO("Not yet implemented")

    actual override suspend fun requestDevice(descriptor: GPUDeviceDescriptor?): Result<GPUDevice> {
        return map(descriptor)
            .let { handler.requestDevice(it) }
            .wait<WGPUDevice>()
            .let(::Device)
    }

    actual override fun close() {
        // Nothing to do on JS
    }
}