@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.native.WGPUBindGroupLayout
import webgpu.native.wgpuBindGroupLayoutRelease

actual class BindGroupLayout(internal val handler: WGPUBindGroupLayout) : AutoCloseable {
    actual override fun close() {
        wgpuBindGroupLayoutRelease(handler)
    }
}