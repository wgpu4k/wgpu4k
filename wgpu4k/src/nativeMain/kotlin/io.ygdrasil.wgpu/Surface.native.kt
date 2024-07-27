@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.*
import webgpu.*

actual class Surface(
    internal val handler: WGPUSurface,
    internal val sizeProvider: () -> Pair<Int, Int>
) : AutoCloseable {

    private var _textureFormat: TextureFormat? = null
    private var _alphaMode: UInt? = null

    actual val width: Int
        get() = sizeProvider().first
    actual val height: Int
        get() = sizeProvider().second

    actual val textureFormat: TextureFormat by lazy {
        _textureFormat ?: error("call first computeSurfaceCapabilities")
    }

    actual fun getCurrentTexture(): Texture = memScoped {
        val surfaceTexture = alloc<WGPUSurfaceTexture>()
        wgpuSurfaceGetCurrentTexture(handler, surfaceTexture.ptr)
        return Texture(surfaceTexture.texture ?: error("no texture available"))
    }

    actual fun present() {
        wgpuSurfacePresent(handler)
    }

    fun computeSurfaceCapabilities(adapter: Adapter) = memScoped {
        val surfaceCapabilities = alloc<WGPUSurfaceCapabilities>()
        wgpuSurfaceGetCapabilities(handler, adapter.handler, surfaceCapabilities.ptr)

        _alphaMode = surfaceCapabilities.alphaModes
            ?.get(0) ?: error("fail to get alphaMode at index 0")
        _textureFormat = (surfaceCapabilities.formats
            ?.get(0)?.toInt() ?: error("fail to get format at index 0"))
            .let { TextureFormat.of(it) ?: error("TextureFormat not found with value $it") }

    }

    actual fun configure(canvasConfiguration: CanvasConfiguration) {

        if (_textureFormat == null) error("call computeSurfaceCapabilities(adapter: Adapter) before configure")

        wgpuSurfaceConfigure(handler, map(canvasConfiguration))
    }

    actual override fun close() {
        wgpuSurfaceRelease(handler)
    }

    private fun map(input: CanvasConfiguration): CValue<WGPUSurfaceConfiguration> = cValue<WGPUSurfaceConfiguration> {
        device = input.device.handler
        usage = input.usage.toFlagInt().toUInt()
        format = input.format.value.toUInt()
        presentMode = WGPUPresentMode_Fifo
        alphaMode = input.alphaMode.value.toUInt()
        width = this@Surface.width.toUInt()
        height = this@Surface.height.toUInt()
    }

}