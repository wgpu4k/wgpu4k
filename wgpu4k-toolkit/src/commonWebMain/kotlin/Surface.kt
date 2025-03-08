package io.ygdrasil.webgpu

actual class Surface(private val handler: CanvasSurface) : AutoCloseable {
    actual val width: UInt
        get() = handler.width
    actual val height: UInt
        get() = handler.height

    actual val preferredCanvasFormat: GPUTextureFormat?
        get() = handler.preferredCanvasFormat

    // @see https://gpuweb.github.io/gpuweb/#canvas-configuration
    actual val supportedFormats: Set<GPUTextureFormat> =
        setOf(GPUTextureFormat.BGRA8Unorm, GPUTextureFormat.RGBA8Unorm, GPUTextureFormat.RGBA16Float)
    actual val supportedAlphaMode: Set<CompositeAlphaMode> =
        setOf(CompositeAlphaMode.Opaque, CompositeAlphaMode.Premultiplied)

    actual fun getCurrentTexture(): SurfaceTexture {
        return handler.getCurrentTexture()
    }

    actual fun present() {
        handler.present()
    }

    actual fun configure(surfaceConfiguration: SurfaceConfiguration) {
        handler.configure(surfaceConfiguration)
    }

    actual override fun close() {
        handler.close()
    }
}