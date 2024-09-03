package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface

actual class CommandBuffer(val handler: Long) : AutoCloseable {
    actual override fun close() {
        JnaInterface.wgpuCommandBufferRelease(handler)
    }

}