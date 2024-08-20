package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class BindGroup(val handler: Long) : AutoCloseable {

    actual override fun close() {
        JniInterfaceV2.wgpuBindGroupRelease(handler)
    }

}