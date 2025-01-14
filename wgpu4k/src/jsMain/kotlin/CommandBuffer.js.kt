package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUCommandBuffer

actual class CommandBuffer(internal val handler: GPUCommandBuffer) : AutoCloseable {
    actual override fun close() {
        // Nothing to do
    }
}