package io.ygdrasil.wgpu

import webgpu.WGPUSampler
import webgpu.wgpuSamplerRelease

actual class Sampler(internal val handler: WGPUSampler) : AutoCloseable {

    actual override fun close() {
        wgpuSamplerRelease(handler)
    }
}