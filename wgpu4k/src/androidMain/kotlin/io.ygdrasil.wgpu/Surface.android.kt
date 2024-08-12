package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

actual class Surface(internal val handler: Long, actual val width: Int, actual val height: Int) : AutoCloseable {

    private var _textureFormat: TextureFormat? = null
    actual val textureFormat: TextureFormat
        get() = _textureFormat ?: error("call computeSurfaceCapabilities first")

    fun computeSurfaceCapabilities(adapter: Adapter) {
        println("computeSurfaceCapabilities")
        _textureFormat = JniInterface.instance.wgpuSurfaceGetFormat(handler, adapter.handler)
            .also { println("_textureFormat $it") }
            .let { TextureFormat.of(it) ?: TextureFormat.rgba8unormsrgb }
    }

    actual fun getCurrentTexture(): Texture {
        return JniInterface.instance.wgpuSurfaceGetCurrentTexture(handler)
            .let { Texture(it) }
    }

    actual fun present() {
        JniInterface.instance.wgpuSurfacePresent(handler)
    }

    actual fun configure(canvasConfiguration: CanvasConfiguration) {
        JniInterface.instance.wgpuSurfaceConfigure(
            handler,
            canvasConfiguration.device.handler,
            canvasConfiguration.usage.toFlagInt(),
            canvasConfiguration.format.value,
            canvasConfiguration.alphaMode.value,
            width, height
        )
    }

    actual override fun close() {
        JniInterface.instance.wgpuSurfaceRelease(handler)
    }

}