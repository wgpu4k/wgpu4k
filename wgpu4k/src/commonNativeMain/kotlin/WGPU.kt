package io.ygdrasil.webgpu


import ffi.NativeAddress
import ffi.memoryScope
import io.ygdrasil.webgpu.mapper.map
import io.ygdrasil.wgpu.WGPUAdapter
import io.ygdrasil.wgpu.WGPUInstance
import io.ygdrasil.wgpu.WGPUInstanceRequestAdapterCallback
import io.ygdrasil.wgpu.WGPURequestAdapterOptions
import io.ygdrasil.wgpu.WGPURequestAdapterStatus_Success
import io.ygdrasil.wgpu.WGPUSType_SurfaceDescriptorFromAndroidNativeWindow
import io.ygdrasil.wgpu.WGPUSType_SurfaceDescriptorFromMetalLayer
import io.ygdrasil.wgpu.WGPUSType_SurfaceDescriptorFromWaylandSurface
import io.ygdrasil.wgpu.WGPUSType_SurfaceDescriptorFromWindowsHWND
import io.ygdrasil.wgpu.WGPUSType_SurfaceDescriptorFromXcbWindow
import io.ygdrasil.wgpu.WGPUSType_SurfaceDescriptorFromXlibWindow
import io.ygdrasil.wgpu.WGPUSurfaceDescriptor
import io.ygdrasil.wgpu.WGPUSurfaceDescriptorFromAndroidNativeWindow
import io.ygdrasil.wgpu.WGPUSurfaceDescriptorFromMetalLayer
import io.ygdrasil.wgpu.WGPUSurfaceDescriptorFromWaylandSurface
import io.ygdrasil.wgpu.WGPUSurfaceDescriptorFromWindowsHWND
import io.ygdrasil.wgpu.WGPUSurfaceDescriptorFromXcbWindow
import io.ygdrasil.wgpu.WGPUSurfaceDescriptorFromXlibWindow
import io.ygdrasil.wgpu.wgpuCreateInstance
import io.ygdrasil.wgpu.wgpuInstanceCreateSurface
import io.ygdrasil.wgpu.wgpuInstanceRelease
import io.ygdrasil.wgpu.wgpuInstanceRequestAdapter

class WGPU(private val handler: WGPUInstance) : AutoCloseable {

    override fun close() {
        wgpuInstanceRelease(handler)
    }

    fun requestAdapter(
        nativeSurface: NativeSurface,
        powerPreference: PowerPreference? = null
    ): Adapter? = memoryScope { scope ->

        val options = WGPURequestAdapterOptions.allocate(scope)
        options.compatibleSurface = nativeSurface.handler
        if (powerPreference != null) options.powerPreference = powerPreference.value.toUInt()

        var fetchedAdapter: WGPUAdapter? = null
        val callback = WGPUInstanceRequestAdapterCallback.allocate(scope) { status, adapter, message, userdata ->
            if (status != WGPURequestAdapterStatus_Success && adapter == null) error("fail to get adapter")
            fetchedAdapter = adapter
        }

        wgpuInstanceRequestAdapter(handler, options, callback, scope.bufferOfAddress(callback.handler).handler)

        fetchedAdapter?.let { Adapter(it) }
    }

    fun getSurfaceFromMetalLayer(metalLayer: NativeAddress): NativeSurface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceDescriptorFromMetalLayer.allocate(scope).apply {
                chain.sType = WGPUSType_SurfaceDescriptorFromMetalLayer
                layer = metalLayer
            }.handler
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::NativeSurface)
    }

    fun getSurfaceFromX11Window(display: NativeAddress, window: ULong): NativeSurface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceDescriptorFromXlibWindow.allocate(scope).apply {
                chain.sType = WGPUSType_SurfaceDescriptorFromXlibWindow
                this.display = display
                this.window = window
            }.handler
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::NativeSurface)
    }

    fun getSurfaceFromXCBWindow(connection: NativeAddress, window: UInt): NativeSurface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceDescriptorFromXcbWindow.allocate(scope).apply {
                chain.sType = WGPUSType_SurfaceDescriptorFromXcbWindow
                this.window = window
                this.connection = connection
            }.handler
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::NativeSurface)
    }

    fun getSurfaceFromAndroidWindow(window: NativeAddress): NativeSurface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceDescriptorFromAndroidNativeWindow.allocate(scope).apply {
                chain.sType = WGPUSType_SurfaceDescriptorFromAndroidNativeWindow
                this.window = window
            }.handler
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::NativeSurface)
    }

    fun getSurfaceFromWaylandWindow(display: NativeAddress, surface: NativeAddress): NativeSurface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceDescriptorFromWaylandSurface.allocate(scope).apply {
                chain.sType = WGPUSType_SurfaceDescriptorFromWaylandSurface
                this.display = display
                this.surface = surface
            }.handler
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::NativeSurface)
    }

    fun getSurfaceFromWindows(hinstance: NativeAddress, hwnd: NativeAddress): NativeSurface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceDescriptorFromWindowsHWND.allocate(scope).apply {
                chain.sType = WGPUSType_SurfaceDescriptorFromWindowsHWND
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

    Vulkan(1 shl 1),
    GL(1 shl 5),
    Metal(1 shl 2),
    DX12(1 shl 3),
    DX11(1 shl 4),
    BrowserWebGPU(1 shl 6),
    Primary(Vulkan.value or Metal.value or DX12.value or BrowserWebGPU.value),
    Secondary(GL.value or DX11.value),
    None(0x00000000);

}

