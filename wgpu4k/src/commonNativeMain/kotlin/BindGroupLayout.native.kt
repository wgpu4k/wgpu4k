package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUBindGroupLayout
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuBindGroupLayoutRelease
import io.ygdrasil.wgpu.wgpuBindGroupLayoutSetLabel

actual class BindGroupLayout(val handler: WGPUBindGroupLayout) : GPUBindGroupLayout {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuBindGroupLayoutSetLabel(handler, newLabel)
        }

    actual override fun close() {
        wgpuBindGroupLayoutRelease(handler)
    }
}