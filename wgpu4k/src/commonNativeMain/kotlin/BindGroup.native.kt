package io.ygdrasil.webgpu

import webgpu.WGPUBindGroup
import webgpu.wgpuBindGroupRelease

actual class BindGroup(internal val handler: WGPUBindGroup) : AutoCloseable {

    actual override fun close() {
        wgpuBindGroupRelease(handler)
    }
}