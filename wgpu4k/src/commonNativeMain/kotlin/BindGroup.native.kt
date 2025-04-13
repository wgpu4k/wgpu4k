package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUBindGroup
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuBindGroupRelease
import io.ygdrasil.wgpu.wgpuBindGroupSetLabel

actual class BindGroup(val handler: WGPUBindGroup) : GPUBindGroup {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuBindGroupSetLabel(handler, newLabel)
        }

    actual override fun close() {
        wgpuBindGroupRelease(handler)
    }
}