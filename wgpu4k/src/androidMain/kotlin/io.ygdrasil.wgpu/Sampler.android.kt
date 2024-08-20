package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface
import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class Sampler(val handler: Long) : AutoCloseable {
    actual override fun close() {
        JniInterfaceV2.wgpuSamplerRelease(handler)
    }

}