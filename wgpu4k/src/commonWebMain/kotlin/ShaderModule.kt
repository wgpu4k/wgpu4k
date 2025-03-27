package io.ygdrasil.webgpu

actual class ShaderModule(val handler: WGPUShaderModule) : GPUShaderModule {
    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override suspend fun getCompilationInfo(): Result<GPUCompilationInfo> {
        TODO("Not yet implemented")
    }

    actual override fun close() {
        // Nothing to do on JS
    }
}

