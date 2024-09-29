@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.ExperimentalForeignApi
import webgpu.native.WGPUShaderModule
import webgpu.native.wgpuShaderModuleRelease

actual class ShaderModule(internal val handler: WGPUShaderModule) : AutoCloseable {
    actual override fun close() {
        wgpuShaderModuleRelease(handler)
    }

}