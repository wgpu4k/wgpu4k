package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k

actual class CommandBuffer(val handler: Long) : AutoCloseable {
    actual override fun close() {
        NativeWgpu4k.wgpuCommandBufferRelease(handler)
    }

}