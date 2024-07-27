@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.WGPUBindGroupLayout
import webgpu.wgpuBindGroupLayoutRelease

actual class BindGroupLayout(internal val handler: WGPUBindGroupLayout) : AutoCloseable {
    actual override fun close() {
        wgpuBindGroupLayoutRelease(handler)
    }
}