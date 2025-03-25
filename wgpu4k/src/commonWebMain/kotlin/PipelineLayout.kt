package io.ygdrasil.webgpu

actual class PipelineLayout(internal var handler: WGPUPipelineLayout) : GPUPipelineLayout {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun close() {
        // Nothing to do on js
    }
}

