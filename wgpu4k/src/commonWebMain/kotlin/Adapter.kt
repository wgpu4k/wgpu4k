package io.ygdrasil.webgpu

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.mapper.map

private val logger = KotlinLogging.logger {}

suspend fun requestAdapter(options: GPURequestAdapterOptions? = null): Result<Adapter> = runCatching {
    val gpu = navigator.gpu ?: error("WebGPU not supported in this browser.")

    when (options) {
        null -> gpu.requestAdapter()
        else -> gpu.requestAdapter(map(options))
    }.wait<WGPUAdapter>()
        .let { Adapter(it) }
}

actual class Adapter(val handler: WGPUAdapter) : GPUAdapter {

    actual override val features: Set<GPUFeatureName> by lazy {
        GPUFeatureName.entries
            .filter { handler.features.has(it.value.asJsString().castAs()) }
            .toSet()
    }

    actual override val limits: GPUSupportedLimits by lazy { map(handler.limits) }

    actual override val info: GPUAdapterInfo
        get() = map(handler.info)

    actual override suspend fun requestDevice(descriptor: GPUDeviceDescriptor?): Result<GPUDevice> {
        return runCatching {
            when (descriptor) {
                null -> handler.requestDevice()
                else -> handler.requestDevice(map(descriptor))
            }.wait<WGPUDevice?>()
                ?.let { Device(it, descriptor?.onUncapturedError)} ?: error("Failed to create a GPU device")
        }
    }

    actual override fun close() {
        // Nothing to do on JS
    }
}


private fun map(input: GPURequestAdapterOptions) = createJsObject<WGPURequestAdapterOptions>().apply {
    featureLevel = input.featureLevel
    input.powerPreference?.let { powerPreference = it.value }
    forceFallbackAdapter = input.forceFallbackAdapter
    xrCompatible = input.xrCompatible
}
