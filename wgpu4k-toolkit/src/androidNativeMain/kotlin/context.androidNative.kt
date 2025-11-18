@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.webgpu

import ffi.NativeAddress
import ffi.Pointer
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.reinterpret


suspend fun androidContextRenderer(window: COpaquePointer, width: Int, height: Int, deferredRendering: Boolean = false): AndroidContext {
    val windowPtr = window.toNativeAddress()
    val instance = WGPU.createInstance() ?: error("Can't create WGPU instance")
    val nativeSurface = instance.getSurfaceFromAndroidWindow(windowPtr) ?: error("Can't create Surface")
    val adapter = instance.requestAdapter(nativeSurface) ?: error("Can't create Adapter")
    val device = adapter.requestDevice().getOrThrow()
    val surface = Surface(nativeSurface, width.toUInt(), height.toUInt())

    nativeSurface.computeSurfaceCapabilities(adapter)

    val renderingContext = when (deferredRendering) {
        true -> TextureRenderingContext(width.toUInt(), height.toUInt(), GPUTextureFormat.RGBA8Unorm, device)
        false -> SurfaceRenderingContext(surface, surface.supportedFormats.first())
    }

    return AndroidContext(
        windowPtr,
        WGPUContext(surface, adapter, device, renderingContext)
    )
}


class AndroidContext(
    val window: NativeAddress,
    val wgpuContext: WGPUContext,
) : AutoCloseable {

    override fun close() {
        wgpuContext.close()
    }
}

private fun COpaquePointer.toNativeAddress(): NativeAddress = Pointer(reinterpret())