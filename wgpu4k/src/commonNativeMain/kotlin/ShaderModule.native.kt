package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUShaderModule
import io.ygdrasil.wgpu.wgpuShaderModuleRelease

actual class ShaderModule(internal val handler: WGPUShaderModule) : GPUShaderModule {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override fun close() {
        wgpuShaderModuleRelease(handler)
    }

    actual override suspend fun getCompilationInfo(): Result<GPUCompilationInfo> {
        TODO("Not yet implemented")
    }
}
