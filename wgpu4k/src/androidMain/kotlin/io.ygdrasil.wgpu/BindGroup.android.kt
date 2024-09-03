package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface

actual class BindGroup(val handler: Long) : AutoCloseable {

    actual override fun close() {
        JnaInterface.wgpuBindGroupRelease(handler)
    }

}