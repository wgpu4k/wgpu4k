package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class TextureView(val handler: Long) : AutoCloseable {
    actual override fun close() {
        JniInterfaceV2.wgpuTextureViewRelease(handler)
    }

}