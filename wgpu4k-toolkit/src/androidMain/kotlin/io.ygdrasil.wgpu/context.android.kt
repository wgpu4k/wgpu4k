package io.ygdrasil.webgpu

import android.view.SurfaceHolder
import ffi.NativeAddress
import io.ygdrasil.nativeHelper.Helper

suspend fun androidContextRenderer(surfaceHolder: SurfaceHolder, width: Int, height: Int, deferredRendering: Boolean = false): AndroidContext {
    val wgpu = WGPU.createInstance(WGPUInstanceBackend.Vulkan) ?: error("Can't create WGPU instance")
    val nativeSurface = Helper.nativeWindowFromSurface(surfaceHolder.surface)
        .let { NativeAddress(it) }
    val surface = wgpu.getSurfaceFromAndroidWindow(nativeSurface) ?: error("Can't create Surface")
    val adapter = wgpu.requestAdapter(surface) ?: error("Can't create Adapter")
    val device = adapter.requestDevice() ?: error("fail to get device")
    surface.computeSurfaceCapabilities(adapter)

    val renderingContext = when (deferredRendering) {
        true -> TextureRenderingContext(width.toUInt(), height.toUInt(), TextureFormat.RGBA8Unorm, device)
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