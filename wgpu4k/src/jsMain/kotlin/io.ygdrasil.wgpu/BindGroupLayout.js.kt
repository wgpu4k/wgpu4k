package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUBindGroupLayout

@JsExport
actual class BindGroupLayout(internal val handler: GPUBindGroupLayout) : AutoCloseable {
    override fun close() {
        // Nothing to do on JS
    }
}