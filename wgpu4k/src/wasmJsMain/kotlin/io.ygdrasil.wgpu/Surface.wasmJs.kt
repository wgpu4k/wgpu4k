package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUCanvasContext
import org.w3c.dom.HTMLCanvasElement

actual class Surface(internal val handler: GPUCanvasContext) : AutoCloseable {
    actual val width: Int
        get() = TODO("Not yet implemented")
    actual val height: Int
        get() = TODO("Not yet implemented")
    actual val textureFormat: TextureFormat
        get() = TODO("Not yet implemented")

    actual fun getCurrentTexture(): Texture {
        TODO("Not yet implemented")
    }

    actual fun present() {
    }

    actual fun configure(canvasConfiguration: CanvasConfiguration) {
    }

    actual override fun close() {
    }

}

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun HTMLCanvasElement.getSurface() = (getContext("webgpu") as? GPUCanvasContext)?.let { Surface(it) }