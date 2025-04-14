package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map

actual class ShaderModule(val handler: WGPUShaderModule) : GPUShaderModule {
    actual override var label: String
        get() = handler.label
        set(value) {
            handler.label = value
        }

    actual override suspend fun getCompilationInfo(): Result<GPUCompilationInfo> = runCatching {
        handler
            .getCompilationInfo()
            .wait<WGPUCompilationInfo>()
            .let { map(it) }
    }

    actual override fun close() {
        // Nothing to do on JS
    }
}

