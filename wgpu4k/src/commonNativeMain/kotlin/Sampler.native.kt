package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.WGPUSampler
import io.ygdrasil.wgpu.wgpuSamplerRelease

actual class Sampler(internal val handler: WGPUSampler) : AutoCloseable {

    actual override fun close() {
        wgpuSamplerRelease(handler)
    }
}