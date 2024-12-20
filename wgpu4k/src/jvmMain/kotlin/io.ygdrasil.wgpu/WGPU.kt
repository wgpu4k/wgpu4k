package io.ygdrasil.wgpu


import ffi.NativeAddress
import ffi.memoryScope
import io.ygdrasil.wgpu.mapper.map
import webgpu.WGPUAdapter
import webgpu.WGPUInstance
import webgpu.WGPURequestAdapterCallback
import webgpu.WGPURequestAdapterCallbackInfo
import webgpu.WGPURequestAdapterOptions
import webgpu.WGPURequestAdapterStatus
import webgpu.WGPUStringView
import webgpu.WGPUSurfaceDescriptor
import webgpu.WGPUSurfaceSourceMetalLayer
import webgpu.WGPUSurfaceSourceWindowsHWND
import webgpu.WGPUSurfaceSourceXlibWindow
import webgpu.wgpuCreateInstance
import webgpu.wgpuInstanceCreateSurface
import webgpu.wgpuInstanceRelease
import webgpu.wgpuInstanceRequestAdapter

class WGPU(private val handler: WGPUInstance) : AutoCloseable {

    override fun close() {
        wgpuInstanceRelease(handler)
    }

    fun requestAdapter(
        surface: Surface,
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
                if (status != 1u && adapter == null) error("fail to get adapter")
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

    fun getSurfaceFromMetalLayer(metalLayer: NativeAddress): Surface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceSourceMetalLayer.allocate(scope).apply {
                chain.sType = 0x00000004u
                layer = metalLayer
            }.handler
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::Surface)
    }

    fun getSurfaceFromX11Window(display: NativeAddress, window: Long): Surface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceSourceXlibWindow.allocate(scope).apply {
                chain.sType = 0x00000006u
                this.display = display
                this.window = window.toULong()
            }.handler
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::Surface)
    }

    fun getSurfaceFromWindows(hinstance: NativeAddress, hwnd: NativeAddress): Surface? = memoryScope { scope ->

        val surfaceDescriptor = WGPUSurfaceDescriptor.allocate(scope).apply {
            nextInChain = WGPUSurfaceSourceWindowsHWND.allocate(scope).apply {
                chain.sType = 0x00000005u
                this.hwnd = hwnd
                this.hinstance = hinstance
            }.handler
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
            ?.let(::Surface)
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

