package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUShaderModule
import io.ygdrasil.wgpu.wgpuShaderModuleRelease

actual class ShaderModule(internal val handler: WGPUShaderModule) : AutoCloseable {
    actual override fun close() {
        wgpuShaderModuleRelease(handler)
    }
}
