package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUSampler
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuSamplerRelease
import io.ygdrasil.wgpu.wgpuSamplerSetLabel

actual class Sampler(val handler: WGPUSampler) : GPUSampler {

    actual override var label: String
        get() = TODO("Not yet implemented")
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuSamplerSetLabel(handler, newLabel)
        }

    actual override fun close() {
        wgpuSamplerRelease(handler)
    }
}