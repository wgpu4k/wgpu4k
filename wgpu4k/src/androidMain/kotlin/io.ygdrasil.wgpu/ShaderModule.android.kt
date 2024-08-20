package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class ShaderModule(val handler: Long) : AutoCloseable {
    actual override fun close() {
        JniInterface.wgpuShaderModuleRelease(handler)
    }

}