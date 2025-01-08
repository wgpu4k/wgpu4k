package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUCanvasContext
import io.ygdrasil.webgpu.internal.js.navigator
import io.ygdrasil.webgpu.mapper.map
import org.w3c.dom.HTMLCanvasElement

actual class Surface(internal val handler: GPUCanvasContext) : AutoCloseable {
    actual val width: UInt
        get() = handler.canvas.width.toUInt()
    actual val height: UInt
        get() = handler.canvas.height.toUInt()

    actual val preferredCanvasFormat: TextureFormat? by lazy {
        navigator.gpu
            ?.getPreferredCanvasFormat()
            ?.let { TextureFormat.of(it) }
    }

    // @see https://gpuweb.github.io/gpuweb/#canvas-configuration
    actual val supportedFormats: Set<TextureFormat> =
        setOf(TextureFormat.BGRA8Unorm, TextureFormat.RGBA8Unorm, TextureFormat.RGBA16Float)
    actual val supportedAlphaMode: Set<CompositeAlphaMode> =
        setOf(CompositeAlphaMode.Opaque, CompositeAlphaMode.Premultiplied)

    actual fun getCurrentTexture(): SurfaceTexture {
        return handler.getCurrentTexture()
            .let(::Texture)
            .let { SurfaceTexture(it, SurfaceTextureStatus.success) }
    }

    actual fun present() {
        // Nothing to do on js
    }

    actual fun configure(surfaceConfiguration: SurfaceConfiguration) {
        map(surfaceConfiguration)
            .let { handler.configure(it) }
    }

    actual override fun close() {
        // Nothing to do on js
    }

}

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun HTMLCanvasElement.getSurface() = (getContext("webgpu") as? GPUCanvasContext)?.let { Surface(it) }