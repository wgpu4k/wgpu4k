package io.ygdrasil.wgpu

import kotlinx.cinterop.*
import webgpu.*

actual class Surface : AutoCloseable(
    internal val handler: WGPUSurface,
    private val sizeProvider: () -> Pair<Int, Int>
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

    actual fun getCurrentTexture(): Texture {
        val surfaceTexture = cValue<WGPUSurfaceTexture>()
        wgpuSurfaceGetCurrentTexture(handler, surfaceTexture)
        return Texture(surfaceTexture.useContents { texture } ?: error("no texture available"))
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

        wgpuSurfaceConfigure(handler, canvasConfiguration.convert())
    }

    actual override fun close() {
        wgpuSurfaceRelease(handler)
    }

    private fun CanvasConfiguration.convert(): CValue<WGPUSurfaceConfiguration> = cValue<WGPUSurfaceConfiguration>() {
        device = this@convert.device.handler
        usage = this@convert.usage.toUInt()
        format = (this@convert.format?.value ?: _textureFormat!!.value).toUInt()
        presentMode = WGPUPresentMode_Fifo
        alphaMode = this@convert.alphaMode?.value?.toUInt() ?: _alphaMode ?: error("")
        width = this@RenderingContext.width.toUInt()
        height = this@RenderingContext.height.toUInt()
    }

}