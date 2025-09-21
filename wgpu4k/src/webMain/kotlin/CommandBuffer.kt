package io.ygdrasil.webgpu

actual class CommandBuffer(val handler: WGPUCommandBuffer) : GPUCommandBuffer {

    actual override var label: String
        get() = handler.label
        set(value) { handler.label = value }

    actual override fun close() {
        // Nothing to do
    }
}