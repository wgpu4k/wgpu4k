package io.ygdrasil.wgpu

import webgpu.WGPUShaderModule
import webgpu.wgpuShaderModuleRelease

actual class ShaderModule(internal val handler: WGPUShaderModule) : AutoCloseable {
    actual override fun close() {
        wgpuShaderModuleRelease(handler)
    }
}
