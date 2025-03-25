package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUBindGroup
import io.ygdrasil.wgpu.wgpuBindGroupRelease

actual class BindGroup(internal val handler: WGPUBindGroup) : GPUBindGroup {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) { }

    actual override fun close() {
        wgpuBindGroupRelease(handler)
    }
}