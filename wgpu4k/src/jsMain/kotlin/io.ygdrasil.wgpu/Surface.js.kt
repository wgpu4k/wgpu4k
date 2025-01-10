package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPUCanvasConfiguration
import io.ygdrasil.webgpu.internal.js.GPUCanvasContext
import io.ygdrasil.webgpu.internal.js.GPUDevice
import org.w3c.dom.HTMLCanvasElement

actual class Surface(private val handler: GPUCanvasContext) : AutoCloseable {

    actual val width: GPUIndex32
        get() = handler.canvas.width.toInt().toUInt()

    actual val height: GPUIndex32
        get() = handler.canvas.height.toInt().toUInt()

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
        return SurfaceTexture(Texture(handler.getCurrentTexture()), SurfaceTextureStatus.success)
    }

    actual override fun close() {
        // Nothing to do on js
    }

    actual fun present() {
        // Nothing to do on js
    }

    actual fun configure(surfaceConfiguration: SurfaceConfiguration) {
        handler.configure(surfaceConfiguration.convert())
    }

    fun SurfaceConfiguration.convert(): GPUCanvasConfiguration = object : GPUCanvasConfiguration {
        override var device: GPUDevice = this@convert.device.handler
        override var format: String = this@convert.format.value
        override var usage: GPUTextureUsageFlags? = this@convert.usage.toFlagInt()
        override var viewFormats: Array<String>? = this@convert.viewFormats.map { it.value }.toTypedArray()
        override var colorSpace: Any? = this@convert.colorSpace
        override var alphaMode: String? = this@convert.alphaMode.value
    }


}

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun HTMLCanvasElement.getSurface() = (getContext("webgpu") as? GPUCanvasContext)?.let { Surface(it) }

