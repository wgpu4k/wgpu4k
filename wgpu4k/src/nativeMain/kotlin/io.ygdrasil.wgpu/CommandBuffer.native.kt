@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.native.WGPUCommandBuffer
import webgpu.native.wgpuCommandBufferRelease

actual class CommandBuffer(internal val handler: WGPUCommandBuffer) : AutoCloseable {
    actual override fun close() {
        wgpuCommandBufferRelease(handler)
    }

}