package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUBindGroup

actual class BindGroup(internal val handler: GPUBindGroup) : AutoCloseable {

    actual override fun close() {
        // Nothing to do on JS
    }

}