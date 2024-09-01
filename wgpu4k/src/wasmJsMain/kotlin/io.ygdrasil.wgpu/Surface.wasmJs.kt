package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUCanvasContext
import io.ygdrasil.wgpu.internal.js.navigator
import io.ygdrasil.wgpu.mapper.map
import org.w3c.dom.HTMLCanvasElement

actual class Surface(internal val handler: GPUCanvasContext) : AutoCloseable {
    actual val width: Int
        get() = handler.canvas.width
    actual val height: Int
        get() = handler.canvas.height

    actual val preferredCanvasFormat: TextureFormat? by lazy {
        navigator.gpu
            ?.getPreferredCanvasFormat()
            ?.let { TextureFormat.of(it) }
    }

    actual val supportedFormats: Set<TextureFormat> = TextureFormat.entries.toSet()
    actual val supportedAlphaMode: Set<CompositeAlphaMode> = setOf(CompositeAlphaMode.opaque, CompositeAlphaMode.premultiplied)

    actual fun getCurrentTexture(): Texture {
        return handler.getCurrentTexture()
            .let(::Texture)
    }

    actual fun present() {
        // Nothing to do on js
    }

    actual fun configure(canvasConfiguration: CanvasConfiguration) {
        map(canvasConfiguration)
            .let { handler.configure(it) }
    }

    actual override fun close() {
        // Nothing to do on js
    }

}

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun HTMLCanvasElement.getSurface() = (getContext("webgpu") as? GPUCanvasContext)?.let { Surface(it) }