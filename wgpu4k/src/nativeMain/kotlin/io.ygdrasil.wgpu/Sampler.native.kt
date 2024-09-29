@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.native.WGPUSampler
import webgpu.native.wgpuSamplerRelease

actual class Sampler(internal val handler: WGPUSampler) : AutoCloseable {
    actual override fun close() {
        wgpuSamplerRelease(handler)
    }
}