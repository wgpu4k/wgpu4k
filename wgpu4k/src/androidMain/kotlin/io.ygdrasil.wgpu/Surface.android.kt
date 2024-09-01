package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface
import io.ygdrasil.wgpu.internal.JniInterface
import io.ygdrasil.wgpu.internal.jna.WGPUSurfaceCapabilities
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress
import java.lang.foreign.ValueLayout

actual class Surface(val handler: Long, actual val width: Int, actual val height: Int) : AutoCloseable {

    private var supportedTextureFormats: List<TextureFormat> = listOf()
    private var supportedAlphaMode: List<CompositeAlphaMode> = listOf()
    actual val textureFormat: TextureFormat
        get() = supportedTextureFormats.firstOrNull() ?: error("call computeSurfaceCapabilities first")

    fun computeSurfaceCapabilities(adapter: Adapter) = scoped { arena ->
        println("computeSurfaceCapabilities")
        val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(arena)
        JnaInterface.wgpuSurfaceGetCapabilities(handler, adapter.handler, surfaceCapabilities.pointer.toAddress())

        val formats = WGPUSurfaceCapabilities.formats(surfaceCapabilities)
        val formatCount = WGPUSurfaceCapabilities.formatCount(surfaceCapabilities)
        println("computeSurfaceCapabilities $formatCount and ptr $formats")
        supportedTextureFormats = (0..formatCount.toInt()).map { index ->
            formats.get(ValueLayout.JAVA_INT, index * ValueLayout.JAVA_INT.size)
                .also { if (it == 0) println("ignoring undefined format") }
                .takeIf { it != 0 }
                ?.let { TextureFormat.of(it) ?: error("fail to map format from $it") }
        }.mapNotNull { it }

        println("supportedTextureFormats: $supportedTextureFormats")

        val alphaModes = WGPUSurfaceCapabilities.alphaModes(surfaceCapabilities)
        val alphaModeCount = WGPUSurfaceCapabilities.alphaModeCount(surfaceCapabilities)
        println("computeSurfaceCapabilities $alphaModeCount and ptr $alphaModes")
        supportedAlphaMode = (0..formatCount.toInt()).map { index ->
            formats.get(ValueLayout.JAVA_INT, index * ValueLayout.JAVA_INT.size)
                .also { if (it == 0) println("ignoring undefined alpha mode") }
                .takeIf { it != 0 }
                ?.let { CompositeAlphaMode.of(it) ?: error("fail to map alpha mode from $it") }
        }.mapNotNull { it }

        println("supportedAlphaMode: $supportedAlphaMode")

        if (supportedTextureFormats.isEmpty()) {
            println("WARNING: fail to get supported textures on surface, will inject rgba8unormsrgb format")
            supportedTextureFormats = listOf(TextureFormat.rgba8unormsrgb)
        }

        if (supportedAlphaMode.isEmpty()) {
            println("WARNING: fail to get supported alpha mode on surface, will inject inherit alpha mode")
            supportedAlphaMode = listOf(CompositeAlphaMode.inherit)
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