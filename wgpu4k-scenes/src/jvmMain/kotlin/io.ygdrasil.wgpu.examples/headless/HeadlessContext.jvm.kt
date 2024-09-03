package io.ygdrasil.wgpu.examples.headless

import io.ygdrasil.wgpu.WGPU
import io.ygdrasil.wgpu.WGPUContext
import io.ygdrasil.wgpu.glfwContextRenderer

actual suspend fun getHeadlessContext(): WGPUContext {
    WGPU.loadLibrary()
    return glfwContextRenderer(deferredRendering = true).wgpuContext
}