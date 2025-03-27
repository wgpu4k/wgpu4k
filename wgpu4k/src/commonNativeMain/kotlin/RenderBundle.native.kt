package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPURenderBundle

actual class RenderBundle(val handler: WGPURenderBundle) : GPURenderBundle {
    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}
}