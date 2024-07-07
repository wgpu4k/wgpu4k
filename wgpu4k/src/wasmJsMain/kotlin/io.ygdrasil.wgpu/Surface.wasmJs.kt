package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUCanvasContext
import io.ygdrasil.wgpu.internal.js.navigator
import io.ygdrasil.wgpu.mapper.map
import org.w3c.dom.HTMLCanvasElement

actual class Surface(internal val handler: GPUCanvasContext) : AutoCloseable {
    actual val width: Int
        get() = TODO("Not yet implemented")
    actual val height: Int
        get() = TODO("Not yet implemented")
    actual val textureFormat: TextureFormat by lazy {
        navigator.gpu
            ?.getPreferredCanvasFormat()
            ?.let { TextureFormat.of(it) }
            ?: error("fail to get canvas prefered format")
    }

    actual fun getCurrentTexture(): Texture {
        TODO("Not yet implemented")
    }

    actual fun present() {
        TODO("Not yet implemented")
    }

    actual fun configure(canvasConfiguration: CanvasConfiguration)  {
        handler.configure(map(canvasConfiguration, textureFormat))
    }

    actual override fun close() {
    }

}

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun HTMLCanvasElement.getSurface() = (getContext("webgpu") as? GPUCanvasContext)?.let { Surface(it) }