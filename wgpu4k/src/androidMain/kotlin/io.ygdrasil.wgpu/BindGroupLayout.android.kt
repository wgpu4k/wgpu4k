package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class BindGroupLayout(val handler: Long) : AutoCloseable {
    actual override fun close() {
        JniInterface.instance.wgpuBindGroupLayoutRelease(handler)
    }
}