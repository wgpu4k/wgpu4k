package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface

actual class BindGroupLayout(val handler: Long) : AutoCloseable {
    actual override fun close() {
        JnaInterface.wgpuBindGroupLayoutRelease(handler)
    }
}