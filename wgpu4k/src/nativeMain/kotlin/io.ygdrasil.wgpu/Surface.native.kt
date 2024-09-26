@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cValue
import kotlinx.cinterop.get
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import webgpu.WGPUSurface
import webgpu.WGPUSurfaceCapabilities
import webgpu.WGPUSurfaceConfiguration
import webgpu.WGPUSurfaceTexture
import webgpu.wgpuSurfaceConfigure
import webgpu.wgpuSurfaceGetCapabilities
import webgpu.wgpuSurfaceGetCurrentTexture
import webgpu.wgpuSurfacePresent
import webgpu.wgpuSurfaceRelease

actual class Surface(
    internal val handler: WGPUSurface,
    internal val sizeProvider: () -> Pair<Int, Int>
) : AutoCloseable {

    private var _supportedFormats: Set<TextureFormat> = setOf()
    private var _supportedAlphaMode: Set<CompositeAlphaMode> = setOf()

    actual val width: Int
        get() = sizeProvider().first
    actual val height: Int
        get() = sizeProvider().second

    actual val preferredCanvasFormat: TextureFormat? = null
    actual val supportedFormats: Set<TextureFormat>
        get() = _supportedFormats
    actual val supportedAlphaMode: Set<CompositeAlphaMode>
        get() = _supportedAlphaMode

    actual fun getCurrentTexture(): SurfaceTexture = memScoped {
        val surfaceTexture = alloc<WGPUSurfaceTexture>()
        wgpuSurfaceGetCurrentTexture(handler, surfaceTexture.ptr)
        return SurfaceTexture(
            Texture(surfaceTexture.texture ?: error("no texture available")),
            SurfaceTextureStatus.of(surfaceTexture.status.toInt()) ?: error("fail to get status"),
        )
    }

    actual fun present() {
        wgpuSurfacePresent(handler)
    }

    fun computeSurfaceCapabilities(adapter: Adapter) = memScoped {
        val surfaceCapabilities = alloc<WGPUSurfaceCapabilities>()
        wgpuSurfaceGetCapabilities(handler, adapter.handler, surfaceCapabilities.ptr)

        val formats = surfaceCapabilities.formats
        val formatCount = surfaceCapabilities.formatCount
        _supportedFormats = (0..formatCount.toInt()).map { index ->
            formats?.get(index)
                ?.let { value -> TextureFormat.of(value.toInt()).also { if (it == null) println("ignoring undefined format with value $value") } }
        }.mapNotNull { it }
            .toSet()

        val alphaModes = surfaceCapabilities.alphaModes
        val alphaModeCount = surfaceCapabilities.alphaModeCount
        _supportedAlphaMode = (0..alphaModeCount.toInt()).map { index ->
            alphaModes?.get(index)
                ?.let { value -> CompositeAlphaMode.of(value.toInt()).also { if (it == null) println("ignoring undefined format with value $value") } }
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

    actual fun configure(surfaceConfiguration: SurfaceConfiguration) {
        wgpuSurfaceConfigure(handler, map(surfaceConfiguration))
    }

    actual override fun close() {
        wgpuSurfaceRelease(handler)
    }

    private fun map(input: SurfaceConfiguration): CValue<WGPUSurfaceConfiguration> = cValue<WGPUSurfaceConfiguration> {
        device = input.device.handler
        usage = input.usage.toFlagInt().toUInt()
        format = input.format.value.toUInt()
        presentMode = input.presentMode.value.toUInt()
        alphaMode = input.alphaMode.value.toUInt()
        width = this@Surface.width.toUInt()
        height = this@Surface.height.toUInt()
    }

}