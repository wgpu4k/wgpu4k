package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUCommandBuffer
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuCommandBufferRelease
import io.ygdrasil.wgpu.wgpuCommandBufferSetLabel

actual class CommandBuffer(val handler: WGPUCommandBuffer) : GPUCommandBuffer {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuCommandBufferSetLabel(handler, newLabel)
        }

    actual override fun close() {
        wgpuCommandBufferRelease(handler)
    }
}