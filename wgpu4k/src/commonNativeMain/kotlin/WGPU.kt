package io.ygdrasil.webgpu


import io.ygdrasil.kffi.NativeAddress
import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUAdapter
import io.ygdrasil.wgpu.WGPUCallbackMode_AllowSpontaneous
import io.ygdrasil.wgpu.WGPUInstance
import io.ygdrasil.wgpu.WGPURequestAdapterCallback
import io.ygdrasil.wgpu.WGPURequestAdapterCallbackInfo
import io.ygdrasil.wgpu.WGPURequestAdapterOptions
import io.ygdrasil.wgpu.WGPURequestAdapterStatus_Success
import io.ygdrasil.wgpu.WGPUSType_SurfaceSourceAndroidNativeWindow
import io.ygdrasil.wgpu.WGPUSType_SurfaceSourceMetalLayer
import io.ygdrasil.wgpu.WGPUSType_SurfaceSourceWaylandSurface
import io.ygdrasil.wgpu.WGPUSType_SurfaceSourceWindowsHWND
import io.ygdrasil.wgpu.WGPUSType_SurfaceSourceXCBWindow
import io.ygdrasil.wgpu.WGPUSType_SurfaceSourceXlibWindow
import io.ygdrasil.wgpu.WGPUSurfaceDescriptor
import io.ygdrasil.wgpu.WGPUSurfaceSourceAndroidNativeWindow
import io.ygdrasil.wgpu.WGPUSurfaceSourceMetalLayer
import io.ygdrasil.wgpu.WGPUSurfaceSourceWaylandSurface
import io.ygdrasil.wgpu.WGPUSurfaceSourceWindowsHWND
import io.ygdrasil.wgpu.WGPUSurfaceSourceXCBWindow
import io.ygdrasil.wgpu.WGPUSurfaceSourceXlibWindow
import io.ygdrasil.wgpu.allocate
import io.ygdrasil.wgpu.register
import io.ygdrasil.wgpu.wgpuAdapterRelease
import io.ygdrasil.wgpu.wgpuCreateInstance
import io.ygdrasil.wgpu.wgpuInstanceCreateSurface
import io.ygdrasil.wgpu.wgpuInstanceRelease
import io.ygdrasil.wgpu.wgpuInstanceRequestAdapter

class WGPU(val handler: WGPUInstance) : AutoCloseable {

    override fun close() {
        wgpuInstanceRelease(handler)
    }

    fun requestAdapter(
        surface: NativeSurface,
        powerPreference: GPUPowerPreference? = null,
    ): Adapter? = memoryScope { scope ->
        val options = WGPURequestAdapterOptions.allocate(scope).apply {
            compatibleSurface = surface.handler
            powerPreference?.let { this.powerPreference = it.value }
        }
        var fetchedAdapter: WGPUAdapter? = null
        var failure: IllegalStateException? = null
        val registration = WGPURequestAdapterCallback.register(
            policy = CallbackPolicy.ONCE,
            callback = WGPURequestAdapterCallback { status, adapter, message, _ ->
                if (status == WGPURequestAdapterStatus_Success && adapter != null) {
                    fetchedAdapter = adapter
                } else {
                    adapter?.let(::wgpuAdapterRelease)
                    failure = IllegalStateException(
                        "requestAdapter failed with status: $status and message: ${message.data?.toKString(message.length)}"
                    )
                }
            },
        )
        try {
            val callbackInfo = WGPURequestAdapterCallbackInfo.allocate(
                scope,
                WGPUCallbackMode_AllowSpontaneous,
                registration,
            )
            wgpuInstanceRequestAdapter(handler, options, callbackInfo)
        } finally {
            registration.close()
        }
        failure?.let { throw it }
        fetchedAdapter?.let(::Adapter)
    }

    fun getSurfaceFromMetalLayer(metalLayer: NativeAddress): NativeSurface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceSourceMetalLayer.allocate(scope).apply {
                chain.sType = WGPUSType_SurfaceSourceMetalLayer
                layer = metalLayer
            }.chain
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::NativeSurface)
    }

    fun getSurfaceFromX11Window(display: NativeAddress, window: ULong): NativeSurface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceSourceXlibWindow.allocate(scope).apply {
                chain.sType = WGPUSType_SurfaceSourceXlibWindow
                this.display = display
                this.window = window
            }.chain
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::NativeSurface)
    }

    fun getSurfaceFromXCBWindow(connection: NativeAddress, window: UInt): NativeSurface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceSourceXCBWindow.allocate(scope).apply {
                chain.sType = WGPUSType_SurfaceSourceXCBWindow
                this.window = window
                this.connection = connection
            }.chain
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::NativeSurface)
    }

    fun getSurfaceFromAndroidWindow(window: NativeAddress): NativeSurface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceSourceAndroidNativeWindow.allocate(scope).apply {
                chain.sType = WGPUSType_SurfaceSourceAndroidNativeWindow
                this.window = window
            }.chain
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::NativeSurface)
    }

    fun getSurfaceFromWaylandWindow(display: NativeAddress, surface: NativeAddress): NativeSurface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceSourceWaylandSurface.allocate(scope).apply {
                chain.sType = WGPUSType_SurfaceSourceWaylandSurface
                this.display = display
                this.surface = surface
            }.chain
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::NativeSurface)
    }

    fun getSurfaceFromWindows(hinstance: NativeAddress, hwnd: NativeAddress): NativeSurface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceSourceWindowsHWND.allocate(scope).apply {
                chain.sType = WGPUSType_SurfaceSourceWindowsHWND
                this.hwnd = hwnd
                this.hinstance = hinstance
            }.chain
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::NativeSurface)
    }

    companion object {

        fun createInstance(backend: WGPUInstanceBackend? = null): WGPU? = memoryScope { scope ->
            backend?.let { scope.map(backend) }
                .let { wgpuCreateInstance(it) }
                ?.let { WGPU(it) }
        }
    }
}

enum class WGPUInstanceBackend(val value: Int) {

    Vulkan(1 shl 0),
    GL(1 shl 1),
    Metal(1 shl 2),
    DX12(1 shl 3),
    DX11(1 shl 4),
    BrowserWebGPU(1 shl 5),
    Primary(Vulkan.value or Metal.value or DX12.value or BrowserWebGPU.value),
    Secondary(GL.value or DX11.value),
    None(0x00000000);

}
