package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUTextureView
import io.ygdrasil.wgpu.wgpuTextureViewRelease

actual class TextureView(internal val handler: WGPUTextureView) : AutoCloseable {
    actual override fun close() {
        wgpuTextureViewRelease(handler)
    }
}