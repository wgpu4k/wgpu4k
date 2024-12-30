package io.ygdrasil.webgpu

import webgpu.WGPUSampler
import webgpu.wgpuSamplerRelease

actual class Sampler(internal val handler: WGPUSampler) : AutoCloseable {

    actual override fun close() {
        wgpuSamplerRelease(handler)
    }
}