package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUBindGroupLayout
import io.ygdrasil.wgpu.wgpuBindGroupLayoutRelease

actual class BindGroupLayout(val handler: WGPUBindGroupLayout) : GPUBindGroupLayout {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override fun close() {
        wgpuBindGroupLayoutRelease(handler)
    }
}