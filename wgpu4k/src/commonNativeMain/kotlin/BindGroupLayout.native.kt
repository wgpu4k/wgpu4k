package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUBindGroupLayout
import io.ygdrasil.wgpu.wgpuBindGroupLayoutRelease

actual class BindGroupLayout(internal val handler: WGPUBindGroupLayout) : GPUBindGroupLayout {

    override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override fun close() {
        wgpuBindGroupLayoutRelease(handler)
    }
}