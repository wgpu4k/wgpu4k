package io.ygdrasil.webgpu

import ffi.ArrayHolder
import ffi.MemoryAllocator
import ffi.MemoryBuffer
import ffi.memoryScope
import io.ygdrasil.wgpu.WGPUCompositeAlphaMode
import io.ygdrasil.wgpu.WGPUSurface
import io.ygdrasil.wgpu.WGPUSurfaceCapabilities
import io.ygdrasil.wgpu.WGPUSurfaceConfiguration
import io.ygdrasil.wgpu.WGPUSurfaceTexture
import io.ygdrasil.wgpu.WGPUTextureFormat
import io.ygdrasil.wgpu.wgpuSurfaceConfigure
import io.ygdrasil.wgpu.wgpuSurfaceGetCapabilities
import io.ygdrasil.wgpu.wgpuSurfaceGetCurrentTexture
import io.ygdrasil.wgpu.wgpuSurfacePresent
import io.ygdrasil.wgpu.wgpuSurfaceRelease

actual class Surface(
    internal val handler: WGPUSurface
) : AutoCloseable {

    private var _supportedFormats: Set<TextureFormat> = setOf()
    private var _supportedAlphaMode: Set<CompositeAlphaMode> = setOf()
    var _width: UInt? = null
    var _height: UInt? = null

    actual val width: UInt
        get() = _width ?: error("width not yet initialized")
    actual val height: UInt
        get() = _height ?: error("height not yet initialized")

    actual val preferredCanvasFormat: TextureFormat? = null
    actual val supportedFormats: Set<TextureFormat>
        get() = _supportedFormats
    actual val supportedAlphaMode: Set<CompositeAlphaMode>
        get() = _supportedAlphaMode

    actual fun getCurrentTexture(): SurfaceTexture = memoryScope { scope ->
        WGPUSurfaceTexture.allocate(scope).let { surfaceTexture ->
            wgpuSurfaceGetCurrentTexture(handler, surfaceTexture)
            surfaceTexture.status
            SurfaceTexture(
                Texture(surfaceTexture.texture ?: error("fail to get texture from surface")),
                SurfaceTextureStatus.of(surfaceTexture.status) ?: error("fail to get status"),
            )
        }
    }

    actual fun present() {
        wgpuSurfacePresent(handler)
    }

    fun computeSurfaceCapabilities(adapter: Adapter) = memoryScope { scope ->
        println("computeSurfaceCapabilities")
        val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(scope)
        wgpuSurfaceGetCapabilities(handler, adapter.handler, surfaceCapabilities)

        val formats = surfaceCapabilities.formats ?: error("fail to get formats")
        _supportedFormats = surfaceCapabilities.toTextureFormats(formats)

        val alphaModes = surfaceCapabilities.alphaModes ?: error("fail to get alpha modes")
        _supportedAlphaMode = surfaceCapabilities.toAlphaMode(alphaModes)

        println("supportedTextureFormats: $supportedFormats")
        println("supportedAlphaMode: $supportedAlphaMode")

        if (_supportedFormats.isEmpty()) {
            println("WARNING: fail to get supported textures on surface, will inject rgba8unorm format")
            _supportedFormats = setOf(TextureFormat.RGBA8Unorm)
        }

        if (_supportedAlphaMode.isEmpty()) {
            println("WARNING: fail to get supported alpha mode on surface, will inject inherit alpha mode")
            _supportedAlphaMode = setOf(CompositeAlphaMode.Inherit)
        }
    }

    private fun WGPUSurfaceCapabilities.toTextureFormats(
        formats: ArrayHolder<WGPUTextureFormat>
    ) = UIntArray(formatCount.toInt()) { 0u }
        .also {
            MemoryBuffer(formats.handler, formatCount * Int.SIZE_BYTES.toULong())
                .readUInts(it)
        }
        .map {
            TextureFormat.of(it)
                .also { if (it == null) println("ignoring undefined format with value $it") }
        }
        .filterNotNull()
        .toSet()

    private fun WGPUSurfaceCapabilities.toAlphaMode(
        alphaModes: ArrayHolder<WGPUCompositeAlphaMode>
    ) = UIntArray(formatCount.toInt()) { 0u }
        .also {
            MemoryBuffer(alphaModes.handler, formatCount * Int.SIZE_BYTES.toULong())
                .readUInts(it)
        }
        .map {
            CompositeAlphaMode.of(it)
                .also { if (it == null) println("ignoring undefined alpha mode with value $it") }
        }
        .filterNotNull()
        .toSet()

    actual fun configure(surfaceConfiguration: SurfaceConfiguration) = memoryScope { scope ->
        wgpuSurfaceConfigure(handler, scope.map(surfaceConfiguration))
    }

    actual override fun close() {
        wgpuSurfaceRelease(handler)
    }

    private fun MemoryAllocator.map(input: SurfaceConfiguration): WGPUSurfaceConfiguration =
        WGPUSurfaceConfiguration.allocate(this).also { output ->
            output.device = input.device.handler
            output.usage = input.usage.toFlagULong()
            output.format = input.format.value
            output.presentMode = input.presentMode.value
            output.alphaMode = input.alphaMode.value
            output.width = width
            output.height = height
        }
}