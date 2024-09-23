package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.jna.WGPUSurfaceCapabilities
import io.ygdrasil.wgpu.internal.jna.WGPUSurfaceConfiguration
import io.ygdrasil.wgpu.internal.jna.WGPUSurfaceTexture
import io.ygdrasil.wgpu.internal.jna.wgpu_h.WGPUPresentMode_Fifo
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator
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
        NativeWgpu4k.wgpuSurfaceGetCapabilities(handler, adapter.handler, surfaceCapabilities.pointer.toAddress())

        val formats = WGPUSurfaceCapabilities.formats(surfaceCapabilities)
        val formatCount = WGPUSurfaceCapabilities.formatCount(surfaceCapabilities)
        _supportedFormats = (0..formatCount.toInt()).map { index ->
            formats.get(ValueLayout.JAVA_INT, index * ValueLayout.JAVA_INT.size)
                .let { value ->
                    TextureFormat.of(value)
                        .also { if (it == null) println("ignoring undefined format with value $value") }
                }

        }.mapNotNull { it }
            .toSet()

        val alphaModes = WGPUSurfaceCapabilities.alphaModes(surfaceCapabilities)
        val alphaModeCount = WGPUSurfaceCapabilities.alphaModeCount(surfaceCapabilities)
        _supportedAlphaMode = (0..alphaModeCount.toInt()).map { index ->
            alphaModes.get(ValueLayout.JAVA_INT, index * ValueLayout.JAVA_INT.size)
                .let { value ->
                    CompositeAlphaMode.of(value)
                        .also { if (it == null) println("ignoring undefined format with value $value") }
                }
        }.mapNotNull { it }
            .toSet()


        println("supportedTextureFormats: $supportedFormats")
        println("supportedAlphaMode: $supportedAlphaMode")

        if (_supportedFormats.isEmpty()) {
            println("WARNING: fail to get supported textures on surface, will inject rgba8unorm format")
            _supportedFormats = setOf(TextureFormat.rgba8unorm)
        }

        if (_supportedAlphaMode.isEmpty()) {
            println("WARNING: fail to get supported alpha mode on surface, will inject inherit alpha mode")
            _supportedAlphaMode = setOf(CompositeAlphaMode.inherit)
        }
    }

    actual fun getCurrentTexture(): SurfaceTexture = scoped { arena ->
        WGPUSurfaceTexture.allocate(arena)
            .let { surfaceTexture ->
                NativeWgpu4k.wgpuSurfaceGetCurrentTexture(handler, surfaceTexture.pointer.toAddress())
                    .let { Texture(WGPUSurfaceTexture.texture(surfaceTexture).pointer.toAddress()) }
                    .let {
                        SurfaceTexture(
                            it,
                            SurfaceTextureStatus.of(WGPUSurfaceTexture.status(surfaceTexture))
                                ?: error("fail to get status"),
                        )
                    }

            }
    }

    actual fun present() {
        NativeWgpu4k.wgpuSurfacePresent(handler)
    }

    actual fun configure(canvasConfiguration: CanvasConfiguration) = scoped { arena ->
        NativeWgpu4k.wgpuSurfaceConfigure(handler, arena.map(canvasConfiguration).pointer.toAddress())
    }

    actual override fun close() {
        NativeWgpu4k.wgpuSurfaceRelease(handler)
    }

    private fun SegmentAllocator.map(input: CanvasConfiguration): MemorySegment =
        WGPUSurfaceConfiguration.allocate(this).also { output ->
            WGPUSurfaceConfiguration.device(
                output,
                MemorySegment(Pointer(input.device.handler), Long.SIZE_BYTES.toLong())
            )
            WGPUSurfaceConfiguration.usage(output, input.usage.toFlagInt())
            WGPUSurfaceConfiguration.format(output, input.format.value)
            WGPUSurfaceConfiguration.presentMode(output, WGPUPresentMode_Fifo())
            WGPUSurfaceConfiguration.alphaMode(output, input.alphaMode.value)
            WGPUSurfaceConfiguration.width(output, width)
            WGPUSurfaceConfiguration.height(output, height)
        }
}