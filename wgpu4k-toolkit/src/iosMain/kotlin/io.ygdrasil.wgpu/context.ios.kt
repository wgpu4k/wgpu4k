@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.webgpu

import kotlinx.cinterop.COpaque
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.interpretCPointer
import kotlinx.cinterop.objcPtr
import kotlinx.cinterop.reinterpret
import platform.MetalKit.MTKView


suspend fun iosContextRenderer(view: MTKView, width: Int, height: Int, deferredRendering: Boolean = false): IosContext {
    val layer = view.layer
    val layerPointer: COpaquePointer = interpretCPointer<COpaque>(layer.objcPtr())!!.reinterpret()
    val instance = WGPU.createInstance() ?: error("Can't create WGPU instance")
    val surface = instance.getSurfaceFromMetalLayer(layerPointer)
        ?.let { Surface(it, { width to height }) } ?: error("Can't create Surface")
    val adapter = instance.requestAdapter(surface) ?: error("Can't create Adapter")
    val device = adapter.requestDevice() ?: error("fail to get device")

    surface.computeSurfaceCapabilities(adapter)

    val renderingContext = when (deferredRendering) {
        true -> TextureRenderingContext(width, height, TextureFormat.rgba8unorm, device)
        false -> SurfaceRenderingContext(surface)
    }

    return IosContext(
        view,
        WGPUContext(surface, adapter, device, renderingContext)
    )
}


class IosContext(
    val view: MTKView,
    val wgpuContext: WGPUContext,
) : AutoCloseable {

    override fun close() {
        wgpuContext.close()
    }
}