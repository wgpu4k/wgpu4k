package io.ygdrasil.wgpu

actual class Surface(internal val handler: Long) : AutoCloseable {
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