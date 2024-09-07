package io.ygdrasil.wgpu

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLCanvasElement

suspend fun canvasContextRenderer(htmlCanvas: HTMLCanvasElement? = null, deferredRendering: Boolean = false, width: Int? = null, height: Int? = null): CanvasContext {

    val canvas = htmlCanvas ?: (document.createElement("canvas") as HTMLCanvasElement).also {
        document.body?.appendChild(it)
        if (deferredRendering) it.hidden = true
    }

    val devicePixelRatio = window.devicePixelRatio
    if (width != null) canvas.width = width else canvas.width = (canvas.clientWidth * devicePixelRatio).toInt()
    if (height != null) canvas.height = height else canvas.height = (canvas.clientHeight * devicePixelRatio).toInt()

    val adapter = requestAdapter() ?: error("No appropriate Adapter found.")
    val device = adapter.requestDevice() ?: error("No appropriate Device found.")
    val surface = canvas.getSurface() ?: error("fail to get context")

    val renderingContext = when (deferredRendering) {
        true -> TextureRenderingContext(256, 256, TextureFormat.rgba8unorm, device)
        false -> SurfaceRenderingContext(surface)
    }

    return CanvasContext(
        canvas,
        WGPUContext(surface, adapter, device, renderingContext)
    )
}

class CanvasContext(
    val canvas: HTMLCanvasElement,
    val wgpuContext: WGPUContext,
) : AutoCloseable {

    override fun close() {
        wgpuContext.close()
    }
}