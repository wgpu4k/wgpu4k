package io.ygdrasil.webgpu

import android.view.SurfaceHolder
import ffi.NativeAddress
import io.ygdrasil.nativeHelper.Helper

suspend fun androidContextRenderer(surfaceHolder: SurfaceHolder, width: Int, height: Int, deferredRendering: Boolean = false): AndroidContext {
    val wgpu = WGPU.createInstance(WGPUInstanceBackend.Vulkan) ?: error("Can't create WGPU instance")
    val window = Helper.nativeWindowFromSurface(surfaceHolder.surface)
        .let { NativeAddress(it) }
    val nativeSurface = wgpu.getSurfaceFromAndroidWindow(window) ?: error("Can't create Surface")
    val adapter = wgpu.requestAdapter(nativeSurface) ?: error("Can't create Adapter")
    val device = adapter.requestDevice().getOrThrow()
    val surface = Surface(nativeSurface, width.toUInt(), height.toUInt())
    nativeSurface.computeSurfaceCapabilities(adapter)

    val renderingContext = when (deferredRendering) {
        true -> TextureRenderingContext(width.toUInt(), height.toUInt(), GPUTextureFormat.RGBA8Unorm, device)
        false -> SurfaceRenderingContext(surface)
    }

    return AndroidContext(
        surfaceHolder,
        WGPUContext(surface, adapter, device, renderingContext)
    )
}


class AndroidContext(
    val surfaceHolder: SurfaceHolder,
    val wgpuContext: WGPUContext,
) : AutoCloseable {

    override fun close() {
        wgpuContext.close()
    }
}