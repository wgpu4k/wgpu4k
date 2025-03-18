package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map

class CanvasSurface(private val handler: WGPUCanvasContext) {
    val width: UInt
        get() = handler.canvas.castAs<HTMLCanvasElement>().width.asUInt()
    val height: UInt
        get() = handler.canvas.castAs<HTMLCanvasElement>().height.asUInt()

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
