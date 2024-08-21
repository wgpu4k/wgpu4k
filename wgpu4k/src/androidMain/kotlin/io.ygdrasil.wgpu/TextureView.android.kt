package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class TextureView(val handler: Long) : AutoCloseable {
    actual override fun close() {
        JniInterface.wgpuTextureViewRelease(handler)
    }

}