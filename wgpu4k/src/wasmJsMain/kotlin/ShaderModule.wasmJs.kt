package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUShaderModule

actual class ShaderModule(internal val handler: GPUShaderModule) : AutoCloseable {

    actual override fun close() {
        // Nothing to do on js
    }

}