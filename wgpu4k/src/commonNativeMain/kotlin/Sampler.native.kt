package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUSampler
import io.ygdrasil.wgpu.wgpuSamplerRelease

actual class Sampler(internal val handler: WGPUSampler) : GPUSampler {

    override var label: String
        get() = TODO("Not yet implemented")
        set(value) {}

    actual override fun close() {
        wgpuSamplerRelease(handler)
    }
}