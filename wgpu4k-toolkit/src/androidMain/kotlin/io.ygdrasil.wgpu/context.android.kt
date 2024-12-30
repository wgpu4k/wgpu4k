package io.ygdrasil.wgpu

import android.view.SurfaceHolder

suspend fun androidContextRenderer(surfaceHolder: SurfaceHolder, width: Int, height: Int, deferredRendering: Boolean = false): AndroidContext {
    val wgpu = WGPU.createInstance(WGPUInstanceBackend.Vulkan) ?: error("Can't create WGPU instance")

    val surface = wgpu.getSurfaceFromAndroidWindow(surfaceHolder)
    val adapter = wgpu.requestAdapter(surface)
    val device = adapter.requestDevice() ?: error("fail to get device")
    surface.computeSurfaceCapabilities(adapter)

    val renderingContext = when (deferredRendering) {
        true -> TextureRenderingContext(width, height, TextureFormat.rgba8unorm, device)
        false -> SurfaceRenderingContext(surface)
    }

    return AndroidContext(
        surfaceHolder,
        WGPUContext(surface, adapter, device, renderingContext)
    )
}


class AndroidContext(
    val surfaceHolder: SurfaceHolder,
    val wgpuContext: WGPUContext,
) : AutoCloseable {

    override fun close() {
        wgpuContext.close()
    }
}