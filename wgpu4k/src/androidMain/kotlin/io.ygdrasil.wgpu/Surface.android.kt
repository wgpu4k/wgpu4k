package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface
import io.ygdrasil.wgpu.internal.JniInterface
import io.ygdrasil.wgpu.internal.jna.WGPUSurfaceCapabilities
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress

actual class Surface(val handler: Long, actual val width: Int, actual val height: Int) : AutoCloseable {

    private var _textureFormat: TextureFormat? = null
    actual val textureFormat: TextureFormat
        get() = _textureFormat ?: error("call computeSurfaceCapabilities first")

    fun computeSurfaceCapabilities(adapter: Adapter) = scoped { arena ->
        println("computeSurfaceCapabilities")
        val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(arena)
        JnaInterface.wgpuSurfaceGetCapabilities(handler, adapter.handler, surfaceCapabilities.pointer.toAddress())


        _textureFormat = JniInterface.wgpuSurfaceGetFormat(handler, adapter.handler)
            .also { println("_textureFormat $it") }
            .let { TextureFormat.of(it) ?: TextureFormat.rgba8unormsrgb }
    }

    actual fun getCurrentTexture(): Texture {
        return JniInterface.wgpuSurfaceGetCurrentTexture(handler)
            .let { Texture(it) }
    }

    actual fun present() {
        JnaInterface.wgpuSurfacePresent(handler)
    }

    actual fun configure(canvasConfiguration: CanvasConfiguration) {
        JniInterface.wgpuSurfaceConfigure(
            handler,
            canvasConfiguration.device.handler,
            canvasConfiguration.usage.toFlagInt(),
            canvasConfiguration.format.value,
            canvasConfiguration.alphaMode.value,
            width, height
        )
    }

    actual override fun close() {
        JnaInterface.wgpuSurfaceRelease(handler)
    }

}