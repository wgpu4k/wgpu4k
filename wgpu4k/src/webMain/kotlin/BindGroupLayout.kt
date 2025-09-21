package io.ygdrasil.webgpu

actual class BindGroupLayout(val handler: WGPUBindGroupLayout) : GPUBindGroupLayout {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun close() {
        // Nothing to do on JS
    }
    
}