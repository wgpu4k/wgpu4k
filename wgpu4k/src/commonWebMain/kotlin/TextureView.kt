package io.ygdrasil.webgpu

actual class TextureView(val handler: WGPUTextureView) : GPUTextureView {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun close() {
        // Nothing to do
    }
}