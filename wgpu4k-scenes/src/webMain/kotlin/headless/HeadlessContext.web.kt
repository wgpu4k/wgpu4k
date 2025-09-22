package io.ygdrasil.webgpu.examples.headless

import io.ygdrasil.webgpu.SurfaceConfiguration
import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.canvasContextRenderer

actual suspend fun getHeadlessContext(): WGPUContext {
    val canvas = canvasContextRenderer(deferredRendering = false, width = 256, height = 256)
    canvas.wgpuContext.surface.configure(
        SurfaceConfiguration(
            canvas.wgpuContext.device,
            canvas.wgpuContext.renderingContext.textureFormat,
        )
    )
    return canvas.wgpuContext
}