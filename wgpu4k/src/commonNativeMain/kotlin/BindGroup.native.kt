package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUBindGroup
import io.ygdrasil.wgpu.wgpuBindGroupRelease

actual class BindGroup(internal val handler: WGPUBindGroup) : AutoCloseable {

    actual override fun close() {
        wgpuBindGroupRelease(handler)
    }
}