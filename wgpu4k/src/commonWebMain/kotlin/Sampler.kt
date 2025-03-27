package io.ygdrasil.webgpu

actual class Sampler(val handler: WGPUSampler) : GPUSampler {
    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun close() {
        // Nothing to do on JS
    }

}