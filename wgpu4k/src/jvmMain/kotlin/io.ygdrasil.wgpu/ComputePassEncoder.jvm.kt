package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.WGPUComputePassEncoder
import io.ygdrasil.wgpu.internal.jvm.wgpuComputePassEncoderRelease

actual class ComputePassEncoder(internal val handler: WGPUComputePassEncoder) : AutoCloseable {
    override fun close() {
        wgpuComputePassEncoderRelease(handler)
    }
}