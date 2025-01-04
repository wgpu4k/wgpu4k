package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUBindGroupLayout
import io.ygdrasil.wgpu.wgpuBindGroupLayoutRelease

actual class BindGroupLayout(internal val handler: WGPUBindGroupLayout) : AutoCloseable {
    actual override fun close() {
        wgpuBindGroupLayoutRelease(handler)
    }
}