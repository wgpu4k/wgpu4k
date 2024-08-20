package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class ShaderModule(val handler: Long) : AutoCloseable {
    actual override fun close() {
        JniInterfaceV2.wgpuShaderModuleRelease(handler)
    }

}