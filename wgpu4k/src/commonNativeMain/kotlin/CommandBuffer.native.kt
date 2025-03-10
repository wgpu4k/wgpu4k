package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUCommandBuffer
import io.ygdrasil.wgpu.wgpuCommandBufferRelease

actual class CommandBuffer(internal val handler: WGPUCommandBuffer) : GPUCommandBuffer {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override fun close() {
        wgpuCommandBufferRelease(handler)
    }
}