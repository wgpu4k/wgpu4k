@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.WGPUBindGroupLayout

actual class BindGroupLayout(internal val handler: WGPUBindGroupLayout) : AutoCloseable {
    actual override fun close() {
        TODO("Not yet implemented")
    }
}