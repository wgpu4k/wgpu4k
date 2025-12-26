package io.ygdrasil.webgpu

import ffi.ArrayHolder
import ffi.MemoryAllocator
import ffi.MemoryBuffer
import ffi.memoryScope
import io.github.oshai.kotlinlogging.KotlinLogging
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

private val logger = KotlinLogging.logger {}

class NativeSurface(
    val handler: WGPUSurface
) : AutoCloseable {

    private var _supportedFormats: Set<GPUTextureFormat> = setOf()
    private var _supportedAlphaMode: Set<CompositeAlphaMode> = setOf()

    val supportedFormats: Set<GPUTextureFormat>
        get() = _supportedFormats
    val supportedAlphaMode: Set<CompositeAlphaMode>
        get() = _supportedAlphaMode

    fun getCurrentTexture(): SurfaceTexture = memoryScope { scope ->
        WGPUSurfaceTexture.allocate(scope).let { surfaceTexture ->
            wgpuSurfaceGetCurrentTexture(handler, surfaceTexture)
            surfaceTexture.status
            SurfaceTexture(
                Texture(surfaceTexture.texture ?: error("fail to get texture from surface"), ""),
                SurfaceTextureStatus.of(surfaceTexture.status) ?: error("fail to get status"),
            )
        }
    }

    fun present() {
        wgpuSurfacePresent(handler)
    }

    fun computeSurfaceCapabilities(adapter: Adapter) = memoryScope { scope ->
        logger.trace { "computeSurfaceCapabilities" }
        val surfaceCapabilities = WGPUSurfaceCapabilities.allocate(scope)
        wgpuSurfaceGetCapabilities(handler, adapter.handler, surfaceCapabilities)

        val formats = surfaceCapabilities.formats ?: error("fail to get formats")
        _supportedFormats = surfaceCapabilities.toTextureFormats(formats)

        val alphaModes = surfaceCapabilities.alphaModes ?: error("fail to get alpha modes")
        _supportedAlphaMode = surfaceCapabilities.toAlphaMode(alphaModes)

        logger.info { "supportedTextureFormats: $supportedFormats" }
        logger.info { "supportedAlphaMode: $supportedAlphaMode" }

        if (_supportedFormats.isEmpty()) {
            logger.warn { "fail to get supported textures on surface, will inject rgba8unorm format" }
            _supportedFormats = setOf(GPUTextureFormat.RGBA8Unorm)
        }

        if (_supportedAlphaMode.isEmpty()) {
            logger.warn { "fail to get supported alpha mode on surface, will inject inherit alpha mode" }
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
            GPUTextureFormat.of(it)
                .also { if (it == null) logger.warn { "ignoring undefined format with value $it" } }
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
                .also { if (it == null) logger.warn { "ignoring undefined alpha mode with value $it" } }
        }
        .filterNotNull()
        .toSet()

    fun configure(surfaceConfiguration: SurfaceConfiguration, width: UInt, height: UInt) = memoryScope { scope ->
        wgpuSurfaceConfigure(handler, scope.map(surfaceConfiguration, width, height))
    }

    override fun close() {
        wgpuSurfaceRelease(handler)
    }

    private fun MemoryAllocator.map(input: SurfaceConfiguration, width: UInt, height: UInt): WGPUSurfaceConfiguration =
        WGPUSurfaceConfiguration.allocate(this).also { output ->
            output.device = (input.device as Device).handler
            output.usage = input.usage.value
            output.format = input.format.value
            output.presentMode = input.presentMode.value
            output.alphaMode = input.alphaMode.value
            output.width = width
            output.height = height
        }
}