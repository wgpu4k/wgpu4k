package io.ygdrasil.webgpu.examples.headless

import ffi.LibraryLoader
import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.glfwContextRenderer

actual suspend fun getHeadlessContext(): WGPUContext {
    LibraryLoader.load()
    return glfwContextRenderer(deferredRendering = true).wgpuContext
}