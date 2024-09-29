@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.native.WGPUBindGroup
import webgpu.native.wgpuBindGroupRelease

actual class BindGroup(internal val handler: WGPUBindGroup) : AutoCloseable {
    actual override fun close() {
        wgpuBindGroupRelease(handler)
    }

}