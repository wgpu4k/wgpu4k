package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface

actual class ShaderModule(val handler: Long) : AutoCloseable {
    actual override fun close() {
        JnaInterface.wgpuShaderModuleRelease(handler)
    }

}