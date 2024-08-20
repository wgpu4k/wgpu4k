package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class TextureView(internal val handler: Long) : AutoCloseable {
    actual override fun close() {
        JniInterfaceV2.wgpuTextureViewRelease(handler)
    }

}