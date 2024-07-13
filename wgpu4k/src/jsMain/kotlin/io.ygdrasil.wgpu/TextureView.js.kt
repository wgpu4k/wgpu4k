package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUTextureView

actual class TextureView(internal val handler: GPUTextureView) : AutoCloseable {

    actual override fun close() {
        // Nothing to do
    }
}