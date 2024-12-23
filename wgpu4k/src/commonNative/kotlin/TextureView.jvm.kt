package io.ygdrasil.wgpu

import webgpu.WGPUTextureView
import webgpu.wgpuTextureViewRelease

actual class TextureView(internal val handler: WGPUTextureView) : AutoCloseable {
    actual override fun close() {
        wgpuTextureViewRelease(handler)
    }
}