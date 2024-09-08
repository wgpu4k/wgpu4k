package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k

actual class BindGroup(val handler: Long) : AutoCloseable {

    actual override fun close() {
        NativeWgpu4k.wgpuBindGroupRelease(handler)
    }

}