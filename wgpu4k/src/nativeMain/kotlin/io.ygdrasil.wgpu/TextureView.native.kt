@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.WGPUTextureView

actual class TextureView(internal val handler: WGPUTextureView) : AutoCloseable {
    actual override fun close() {
        TODO("Not yet implemented")
    }

}