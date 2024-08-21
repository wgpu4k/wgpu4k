package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class Sampler(val handler: Long) : AutoCloseable {
    actual override fun close() {
        JniInterface.wgpuSamplerRelease(handler)
    }

}