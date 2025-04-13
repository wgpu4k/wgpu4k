package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUShaderModule
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuShaderModuleRelease
import io.ygdrasil.wgpu.wgpuShaderModuleSetLabel

actual class ShaderModule(val handler: WGPUShaderModule) : GPUShaderModule {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuShaderModuleSetLabel(handler, newLabel)
        }

    actual override fun close() {
        wgpuShaderModuleRelease(handler)
    }

    actual override suspend fun getCompilationInfo(): Result<GPUCompilationInfo> {
        TODO("Not yet implemented")
    }
}
