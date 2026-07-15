package io.ygdrasil.webgpu

import io.ygdrasil.kffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUShaderModule
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuShaderModuleRelease
import io.ygdrasil.wgpu.wgpuShaderModuleSetLabel

actual class ShaderModule(val handler: WGPUShaderModule, label: String) : GPUShaderModule {

    actual override var label: String = label
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuShaderModuleSetLabel(handler, newLabel)
            field = value
        }

    actual override fun close() {
        wgpuShaderModuleRelease(handler)
    }

    actual override suspend fun getCompilationInfo(): Result<GPUCompilationInfo> = Result.failure(
        UnsupportedOperationException(
            "Shader compilation info is not supported by wgpu-native 29.0.0.0"
        )
    )
}
