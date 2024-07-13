package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUBindGroup

actual class BindGroup(val handler: GPUBindGroup) : AutoCloseable {
    actual override fun close() {
        // Nothing to do on js
    }
}