package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class CommandBuffer(val handler: Long) : AutoCloseable {
    actual override fun close() {
        JniInterface.instance.wgpuCommandBufferRelease(handler)
    }

}