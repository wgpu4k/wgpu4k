package io.ygdrasil.wgpu

import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLCanvasElement

suspend fun canvasContextRenderer(htmlCanvas: HTMLCanvasElement? = null, deferredRendering: Boolean = false): CanvasContext {

    val canvas = htmlCanvas ?: (document.createElement("canvas") as HTMLCanvasElement).also {
        it.hidden = true
    }

    val devicePixelRatio = window.devicePixelRatio
    canvas.width = (canvas.clientWidth * devicePixelRatio).toInt()
    canvas.height = (canvas.clientHeight * devicePixelRatio).toInt()
    val adapter = requestAdapter() ?: error("No appropriate Adapter found.")
    val device = adapter.requestDevice() ?: error("No appropriate Device found.")
    val surface = canvas.getSurface() ?: error("fail to get context")

    val renderingContext = when (deferredRendering) {
        true -> TextureRenderingContext(256, 256, TextureFormat.rgba8unormsrgb, device)
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