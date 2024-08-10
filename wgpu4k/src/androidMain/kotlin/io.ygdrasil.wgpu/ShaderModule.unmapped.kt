package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class ShaderModule(internal val handler: Long) : AutoCloseable {
    actual override fun close() {
        JniInterface.instance.wgpuShaderModuleRelease(handler)
    }

}