package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.web.GPUCanvasContext
import io.ygdrasil.webgpu.internal.web.navigator
import io.ygdrasil.webgpu.mapper.map

class CanvasSurface(private val handler: GPUCanvasContext) {
    val width: UInt
        get() = map(handler.canvas.width)
    val height: UInt
        get() = map(handler.canvas.width)

    val preferredCanvasFormat: TextureFormat?
        get() = navigator.gpu
            ?.getPreferredCanvasFormat()
            ?.let { TextureFormat.of(it) }

    fun getCurrentTexture(): SurfaceTexture {
        return handler.getCurrentTexture()
            .let { SurfaceTexture(map(it), SurfaceTextureStatus.success) }
    }

    fun present() {
        // nothing to do on web
    }

    fun configure(surfaceConfiguration: SurfaceConfiguration) {
        map(surfaceConfiguration)
            .let { handler.configure(it) }
    }

    fun close() {
        // nothing to do on web
    }
}
