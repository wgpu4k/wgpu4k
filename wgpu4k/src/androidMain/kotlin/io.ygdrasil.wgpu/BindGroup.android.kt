package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class BindGroup(internal val handler: Long) : AutoCloseable {

    actual override fun close() {
        JniInterface.instance.wgpuBindGroupRelease(handler)
    }

}