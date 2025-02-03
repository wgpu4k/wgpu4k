package io.ygdrasil.webgpu


import ffi.NativeAddress
import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUAdapter
import io.ygdrasil.wgpu.WGPUInstance
import io.ygdrasil.wgpu.WGPURequestAdapterCallback
import io.ygdrasil.wgpu.WGPURequestAdapterCallbackInfo
import io.ygdrasil.wgpu.WGPURequestAdapterOptions
import io.ygdrasil.wgpu.WGPURequestAdapterStatus
import io.ygdrasil.wgpu.WGPURequestAdapterStatus_Success
import io.ygdrasil.wgpu.WGPUSType_SurfaceSourceAndroidNativeWindow
import io.ygdrasil.wgpu.WGPUSType_SurfaceSourceMetalLayer
import io.ygdrasil.wgpu.WGPUSType_SurfaceSourceWaylandSurface
import io.ygdrasil.wgpu.WGPUSType_SurfaceSourceWindowsHWND
import io.ygdrasil.wgpu.WGPUSType_SurfaceSourceXCBWindow
import io.ygdrasil.wgpu.WGPUSType_SurfaceSourceXlibWindow
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.WGPUSurfaceDescriptor
import io.ygdrasil.wgpu.WGPUSurfaceSourceAndroidNativeWindow
import io.ygdrasil.wgpu.WGPUSurfaceSourceMetalLayer
import io.ygdrasil.wgpu.WGPUSurfaceSourceWaylandSurface
import io.ygdrasil.wgpu.WGPUSurfaceSourceWindowsHWND
import io.ygdrasil.wgpu.WGPUSurfaceSourceXCBWindow
import io.ygdrasil.wgpu.WGPUSurfaceSourceXlibWindow
import io.ygdrasil.wgpu.wgpuCreateInstance
import io.ygdrasil.wgpu.wgpuInstanceCreateSurface
import io.ygdrasil.wgpu.wgpuInstanceRelease
import io.ygdrasil.wgpu.wgpuInstanceRequestAdapter

class WGPU(private val handler: WGPUInstance) : AutoCloseable {

    override fun close() {
        wgpuInstanceRelease(handler)
    }

    fun requestAdapter(
        surface: NativeSurface,
        powerPreference: PowerPreference? = null
    ): Adapter? = memoryScope { scope ->

        val options = WGPURequestAdapterOptions.allocate(scope)
        options.compatibleSurface = surface.handler
        if (powerPreference != null) options.powerPreference = powerPreference.value.toUInt()

        var fetchedAdapter: WGPUAdapter? = null
        val callback = WGPURequestAdapterCallback.allocate(scope, object : WGPURequestAdapterCallback {
            override fun invoke(
                status: WGPURequestAdapterStatus,
                adapter: WGPUAdapter?,
                message: WGPUStringView?,
                userdata1: NativeAddress?,
                userdata2: NativeAddress?
            ) {
                if (status != WGPURequestAdapterStatus_Success && adapter == null) error("fail to get adapter")
                fetchedAdapter = adapter
            }
        })

        val callbackInfo = WGPURequestAdapterCallbackInfo.allocate(scope).apply {
            this.callback = callback
            this.userdata2 = scope.bufferOfAddress(callback.handler).handler
        }

        wgpuInstanceRequestAdapter(handler, options, callbackInfo)

        fetchedAdapter?.let { Adapter(it) }
    }

    fun getSurfaceFromMetalLayer(metalLayer: NativeAddress): NativeSurface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceSourceMetalLayer.allocate(scope).apply {
                chain.sType = WGPUSType_SurfaceSourceMetalLayer
                layer = metalLayer
            }.handler
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
            }.handler
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
            }.handler
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::NativeSurface)
    }

    fun getSurfaceFromAndroidWindow(window: NativeAddress): NativeSurface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceSourceAndroidNativeWindow.allocate(scope).apply {
                chain.sType = WGPUSType_SurfaceSourceAndroidNativeWindow
                this.window = window
            }.handler
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
            }.handler
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
            }.handler
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

