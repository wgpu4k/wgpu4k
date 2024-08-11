package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class Surface(internal val handler: Long) : AutoCloseable {
    actual val width: Int
        get() = TODO("Not yet implemented")
    actual val height: Int
        get() = TODO("Not yet implemented")
    actual val textureFormat: TextureFormat
        get() = TODO("Not yet implemented")

    actual fun getCurrentTexture(): Texture {
        return JniInterface.instance.wgpuSurfaceGetCurrentTexture(handler)
            .let { Texture(it) }
    }

    actual fun present() {
        JniInterface.instance.wgpuSurfacePresent(handler)
    }

    actual fun configure(canvasConfiguration: CanvasConfiguration) {
        TODO("Not yet implemented")
    }

    actual override fun close() {
        JniInterface.instance.wgpuSurfaceRelease(handler)
    }

}