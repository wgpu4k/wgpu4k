package io.ygdrasil.webgpu

actual class RenderBundle(val handler: WGPURenderBundle): GPURenderBundle {
    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }
}