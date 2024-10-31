package io.ygdrasil.wgpu

import webgpu.WGPUBindGroupLayout
import webgpu.wgpuBindGroupLayoutRelease

actual class BindGroupLayout(internal val handler: WGPUBindGroupLayout) : AutoCloseable {
    actual override fun close() {
        wgpuBindGroupLayoutRelease(handler)
    }
}