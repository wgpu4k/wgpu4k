package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface

actual class TextureView(val handler: Long) : AutoCloseable {
    actual override fun close() {
        JnaInterface.wgpuTextureViewRelease(handler)
    }

}