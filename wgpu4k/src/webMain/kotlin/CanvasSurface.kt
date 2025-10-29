@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.mapper.map
import web.html.HTMLCanvasElement
import kotlin.OptIn
import kotlin.UInt
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.unsafeCast
import kotlin.let
import kotlin.toUInt

class CanvasSurface(val handler: WGPUCanvasContext) {
    val width: UInt
        get() = handler.canvas.unsafeCast<HTMLCanvasElement>().width.toUInt()
    val height: UInt
        get() = handler.canvas.unsafeCast<HTMLCanvasElement>().height.toUInt()

    val preferredCanvasFormat: GPUTextureFormat?
        get() = navigator.gpu
            ?.getPreferredCanvasFormat()
            ?.let { GPUTextureFormat.of(it) }

    fun getCurrentTexture(): SurfaceTexture {
        return handler.getCurrentTexture()
            .let { Texture(it, canBeDestroy = false) }
            .let { SurfaceTexture(it, SurfaceTextureStatus.success) }
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
