package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface
import io.ygdrasil.wgpu.internal.JniInterface
import io.ygdrasil.wgpu.internal.jna.WGPUSurfaceCapabilities
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress
import java.lang.foreign.ValueLayout

actual class Surface(val handler: Long, actual val width: Int, actual val height: Int) : AutoCloseable {

    private var _supportedFormats: Set<TextureFormat> = setOf()
    private var _supportedAlphaMode: Set<CompositeAlphaMode> = setOf()

    actual val preferredCanvasFormat: TextureFormat? = null
    actual val supportedFormats: Set<TextureFormat>
        get() = _supportedFormats
    actual val supportedAlphaMode: Set<CompositeAlphaMode>
        get() = _supportedAlphaMode

    fun computeSurfaceCapabilities(adapter: Adapter) = scoped { arena ->
        println("computeSurfaceCapabilities")
        val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(arena)
        JnaInterface.wgpuSurfaceGetCapabilities(handler, adapter.handler, surfaceCapabilities.pointer.toAddress())

        val formats = WGPUSurfaceCapabilities.formats(surfaceCapabilities)
        val formatCount = WGPUSurfaceCapabilities.formatCount(surfaceCapabilities)
        _supportedFormats = (0..formatCount.toInt()).map { index ->
            formats.get(ValueLayout.JAVA_INT, index * ValueLayout.JAVA_INT.size)
                .let { value -> TextureFormat.of(value).also { if (it == null) println("ignoring undefined format with value $value") } }

        }.mapNotNull { it }
            .toSet()

        val alphaModes = WGPUSurfaceCapabilities.alphaModes(surfaceCapabilities)
        val alphaModeCount = WGPUSurfaceCapabilities.alphaModeCount(surfaceCapabilities)
        _supportedAlphaMode = (0..alphaModeCount.toInt()).map { index ->
            alphaModes.get(ValueLayout.JAVA_INT, index * ValueLayout.JAVA_INT.size)
                .let { value -> CompositeAlphaMode.of(value).also { if (it == null) println("ignoring undefined format with value $value") } }
        }.mapNotNull { it }
            .toSet()


        println("supportedTextureFormats: $supportedFormats")
        println("supportedAlphaMode: $supportedAlphaMode")

        if (_supportedFormats.isEmpty()) {
            println("WARNING: fail to get supported textures on surface, will inject rgba8unormsrgb and rgba8unorm format")
            _supportedFormats = setOf(TextureFormat.rgba8unormsrgb, TextureFormat.rgba8unorm)
        }

        if (_supportedAlphaMode.isEmpty()) {
            println("WARNING: fail to get supported alpha mode on surface, will inject inherit alpha mode")
            _supportedAlphaMode = setOf(CompositeAlphaMode.inherit)
        }
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