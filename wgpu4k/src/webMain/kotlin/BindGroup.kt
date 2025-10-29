package io.ygdrasil.webgpu

actual class BindGroup(val handler: WGPUBindGroup) : GPUBindGroup {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun close() {
        // Nothing to do on Web
    }

}