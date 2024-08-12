package io.ygdrasil.wgpu

import android.view.SurfaceHolder

suspend fun glfwContextRenderer(surfaceHolder: SurfaceHolder, width: Int? = null, height: Int? = null, deferredRendering: Boolean = false): AndroidContext {
    val wgpu = WGPU.createInstance()
    val surface = wgpu.getSurface(surfaceHolder)
    val adapter = wgpu.requestAdapter(surface)
    val device = adapter.requestDevice() ?: error("fail to get device")

    println("device ${device.handler}")

    val renderingContext = when (deferredRendering) {
        true -> TextureRenderingContext(width ?: error("width must be set on deferred rendering"), height ?: error("height must be set on deferred rendering"), TextureFormat.rgba8unormsrgb, device)
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