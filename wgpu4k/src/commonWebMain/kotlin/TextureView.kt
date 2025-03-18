package io.ygdrasil.webgpu

actual class TextureView(internal val handler: WGPUTextureView) : GPUTextureView {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun close() {
        // Nothing to do
    }
}