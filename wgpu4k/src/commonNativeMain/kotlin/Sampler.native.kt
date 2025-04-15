package io.ygdrasil.webgpu

import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUSampler
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.wgpuSamplerRelease
import io.ygdrasil.wgpu.wgpuSamplerSetLabel

actual class Sampler(val handler: WGPUSampler, label: String) : GPUSampler {

    actual override var label: String = label
        set(value) = memoryScope { scope ->
            val newLabel = WGPUStringView.allocate(scope)
                .also { scope.map(value, it) }
            wgpuSamplerSetLabel(handler, newLabel)
            field = value
        }

    actual override fun close() {
        wgpuSamplerRelease(handler)
    }
}