package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUBindGroupLayout

actual class BindGroupLayout(internal val handler: GPUBindGroupLayout) : AutoCloseable {
    actual override fun close() {
        // Nothing to do on JS
    }
}