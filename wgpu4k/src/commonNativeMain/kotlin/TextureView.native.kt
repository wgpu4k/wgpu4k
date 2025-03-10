package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUTextureView
import io.ygdrasil.wgpu.wgpuTextureViewRelease

actual class TextureView(internal val handler: WGPUTextureView) : GPUTextureView {

    override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override fun close() {
        wgpuTextureViewRelease(handler)
    }
}