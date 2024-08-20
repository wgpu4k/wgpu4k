package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class BindGroup(val handler: Long) : AutoCloseable {

    actual override fun close() {
        JniInterface.wgpuBindGroupRelease(handler)
    }

}