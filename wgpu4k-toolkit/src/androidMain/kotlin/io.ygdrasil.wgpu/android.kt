package io.ygdrasil.wgpu

import android.view.SurfaceHolder

suspend fun glfwContextRenderer(surfaceHolder: SurfaceHolder, width: Int? = null, height: Int? = null, deferredRendering: Boolean = false): AndroidContext {
    val wgpu = WGPU.createInstance()
    val surface = wgpu.getSurface(surfaceHolder)
    val adapter = wgpu.requestAdapter(surface)
    val device = adapter.requestDevice() ?: error("fail to get device")

    println("device ${device.handler}")
    TODO()
}


class AndroidContext(
    val surface: Surface,
    val wgpuContext: WGPUContext,
) : AutoCloseable {

    override fun close() {
        wgpuContext.close()
    }
}