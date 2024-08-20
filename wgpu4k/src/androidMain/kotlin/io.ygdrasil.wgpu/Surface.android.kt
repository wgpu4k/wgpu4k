package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterfaceV2

actual class Surface(val handler: Long, actual val width: Int, actual val height: Int) : AutoCloseable {

    private var _textureFormat: TextureFormat? = null
    actual val textureFormat: TextureFormat
        get() = _textureFormat ?: error("call computeSurfaceCapabilities first")

    fun computeSurfaceCapabilities(adapter: Adapter) {
        println("computeSurfaceCapabilities")
        _textureFormat = JniInterfaceV2.wgpuSurfaceGetFormat(handler, adapter.handler)
            .also { println("_textureFormat $it") }
            .let { TextureFormat.of(it) ?: TextureFormat.rgba8unormsrgb }
    }

    actual fun getCurrentTexture(): Texture {
        return JniInterfaceV2.wgpuSurfaceGetCurrentTexture(handler)
            .let { Texture(it) }
    }

    actual fun present() {
        JniInterfaceV2.wgpuSurfacePresent(handler)
    }

    actual fun configure(canvasConfiguration: CanvasConfiguration) {
        JniInterfaceV2.wgpuSurfaceConfigure(
            handler,
            canvasConfiguration.device.handler,
            canvasConfiguration.usage.toFlagInt(),
            canvasConfiguration.format.value,
            canvasConfiguration.alphaMode.value,
            width, height
        )
    }

    actual override fun close() {
        JniInterfaceV2.wgpuSurfaceRelease(handler)
    }

}