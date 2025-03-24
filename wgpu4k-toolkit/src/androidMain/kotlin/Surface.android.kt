package io.ygdrasil.webgpu

actual class Surface(private val handler: NativeSurface, actual val width: UInt, actual val height: UInt) : AutoCloseable {

    actual val supportedFormats: Set<GPUTextureFormat>
        get() = handler.supportedFormats
    actual val supportedAlphaMode: Set<CompositeAlphaMode>
        get() = handler.supportedAlphaMode

    actual fun getCurrentTexture(): SurfaceTexture = handler.getCurrentTexture()

    actual fun present() = handler.present()

    actual fun configure(surfaceConfiguration: SurfaceConfiguration) {
        handler.configure(surfaceConfiguration, width, height)
    }

    actual override fun close() = handler.close()

}