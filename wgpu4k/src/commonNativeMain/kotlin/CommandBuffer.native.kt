package io.ygdrasil.webgpu

import webgpu.WGPUCommandBuffer
import webgpu.wgpuCommandBufferRelease

actual class CommandBuffer(internal val handler: WGPUCommandBuffer) : AutoCloseable {

    actual override fun close() {
        wgpuCommandBufferRelease(handler)
    }
}