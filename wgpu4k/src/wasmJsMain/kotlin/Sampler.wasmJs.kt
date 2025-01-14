package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUSampler

actual class Sampler(internal val handler: GPUSampler) : AutoCloseable {

    actual override fun close() {
        // Nothing to do on JS
    }

}