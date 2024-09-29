@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.native.WGPUTextureView
import webgpu.native.wgpuTextureViewRelease

actual class TextureView(internal val handler: WGPUTextureView) : AutoCloseable {
    actual override fun close() {
        wgpuTextureViewRelease(handler)
    }

}