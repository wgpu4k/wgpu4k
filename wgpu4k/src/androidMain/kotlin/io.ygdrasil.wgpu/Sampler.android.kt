package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface

actual class Sampler(val handler: Long) : AutoCloseable {
    actual override fun close() {
        JnaInterface.wgpuSamplerRelease(handler)
    }

}