package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUBindGroup

actual class BindGroup(val handler: GPUBindGroup) : AutoCloseable {
    actual override fun close() {
        // Nothing to do on js
    }
}