package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUCommandBuffer
import io.ygdrasil.wgpu.wgpuCommandBufferRelease

actual class CommandBuffer(internal val handler: WGPUCommandBuffer) : AutoCloseable {

    actual override fun close() {
        wgpuCommandBufferRelease(handler)
    }
}