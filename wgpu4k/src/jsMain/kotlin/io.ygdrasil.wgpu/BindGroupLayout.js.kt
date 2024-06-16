package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUBindGroupLayout

@JsExport
actual class BindGroupLayout(internal val handler: GPUBindGroupLayout) : AutoCloseable {
    actual override fun close() {
        // Nothing to do on JS
    }
}