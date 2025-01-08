package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUTextureView

actual class TextureView(internal val handler: GPUTextureView) : AutoCloseable {

    actual override fun close() {
        // Nothing to do here
    }

}