@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.WGPUShaderModule

actual class ShaderModule(internal val handler: WGPUShaderModule) : AutoCloseable {
    actual override fun close() {
        TODO("Not yet implemented")
    }

}