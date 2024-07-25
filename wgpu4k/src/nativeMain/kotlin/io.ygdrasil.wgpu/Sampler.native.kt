@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.WGPUSampler

actual class Sampler(internal val handler: WGPUSampler) : AutoCloseable {
    actual override fun close() {
    }

}