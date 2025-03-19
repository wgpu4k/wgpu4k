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

    return navigator?.gpu?.requestAdapter()?.wait<WGPUAdapter?>()?.let {
        Adapter(it)
    }
}

actual class Adapter(val handler: WGPUAdapter) : GPUAdapter {

    actual override val features: Set<GPUFeatureName> by lazy {
        GPUFeatureName.entries
            .filter { handler.features.has(it.value.asJsString().castAs()) }
            .toSet()
    }

    actual override val limits: GPUSupportedLimits by lazy { map(handler.limits) }

    actual override val info: GPUAdapterInfo
        get() = TODO("Not yet implemented")
    actual override val isFallbackAdapter: Boolean
        get() = handler.isFallbackAdapter

    actual override suspend fun requestDevice(descriptor: GPUDeviceDescriptor?): Result<GPUDevice> {
        return runCatching {
            when (descriptor) {
                null -> handler.requestDevice()
                else -> handler.requestDevice(map(descriptor))
            }.wait<WGPUDevice?>()
                ?.let(::Device) ?: error("Failed to create a GPU device")
        }
    }

    actual override fun close() {
        // Nothing to do on JS
    }
}