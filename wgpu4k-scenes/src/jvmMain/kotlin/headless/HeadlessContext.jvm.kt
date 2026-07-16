package io.ygdrasil.webgpu.examples.headless

import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.glfwContextRenderer

actual suspend fun getHeadlessContext(): WGPUContext {
    return glfwContextRenderer(deferredRendering = true).wgpuContext
}