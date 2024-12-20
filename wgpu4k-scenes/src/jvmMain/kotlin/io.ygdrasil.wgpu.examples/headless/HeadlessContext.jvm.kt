package io.ygdrasil.wgpu.examples.headless

import ffi.LibraryLoader
import io.ygdrasil.wgpu.WGPUContext
import io.ygdrasil.wgpu.glfwContextRenderer

actual suspend fun getHeadlessContext(): WGPUContext {
    LibraryLoader.load()
    return glfwContextRenderer(deferredRendering = true).wgpuContext
}