@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.*
import webgpu.native.*

private var lastFindAdapter: WGPUAdapter? = null

class WGPU(val handler: WGPUInstance) : AutoCloseable {

    fun requestAdapter(
        renderingContext: Surface,
        powerPreference: WGPUPowerPreference = WGPUPowerPreference_Undefined
    ): Adapter? {
        lastFindAdapter = null
        val options = cValue<WGPURequestAdapterOptions> {
            compatibleSurface = renderingContext.handler
            this.powerPreference = powerPreference
        }

        val handleRequestAdapter =
            staticCFunction<WGPURequestAdapterStatus, WGPUAdapter, CPointer<ByteVar>?, COpaquePointer, Unit> { status, adapter, message, userData ->
                println("WGPURequestAdapterCallback ${userData} ${adapter}")
                if (status == WGPURequestAdapterStatus_Success) {
                    lastFindAdapter = adapter
                } else {
                    println("request_adapter status=$status message=${message?.toKStringFromUtf8()}\n")
                }
            }

        wgpuInstanceRequestAdapter(handler, options, handleRequestAdapter.reinterpret(), null)

        return lastFindAdapter?.let { Adapter(it) }
    }

    fun getSurfaceFromX11Window(display: COpaquePointer, window: ULong) = memScoped {
        val surfaceDescriptor = cValue<WGPUSurfaceDescriptor> {
            nextInChain = cValue<WGPUSurfaceDescriptorFromXlibWindow> {
                chain.sType = WGPUSType_SurfaceDescriptorFromXlibWindow
                this.display = display
                this.window = window
            }.ptr.reinterpret()
        }

        wgpuInstanceCreateSurface(handler, surfaceDescriptor)
    }

    fun getSurfaceFromWindows(hinstance: COpaquePointer, hwnd: COpaquePointer) = memScoped {
        val surfaceDescriptor = cValue<WGPUSurfaceDescriptor> {
            nextInChain = cValue<WGPUSurfaceDescriptorFromWindowsHWND> {
                chain.sType = WGPUSType_SurfaceDescriptorFromWindowsHWND
                this.hwnd = hwnd
                this.hinstance = hinstance
            }.ptr.reinterpret()
        }

        wgpuInstanceCreateSurface(handler, surfaceDescriptor)
    }

    fun getSurfaceFromMetalLayer(metalLayer: COpaquePointer): WGPUSurface? = memScoped {
        val surfaceDescriptor = cValue<WGPUSurfaceDescriptor> {
            nextInChain = cValue<WGPUSurfaceDescriptorFromMetalLayer> {
                chain.sType = WGPUSType_SurfaceDescriptorFromMetalLayer
                layer = metalLayer
            }.ptr.reinterpret()
        }

        return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
    }

    companion object {

        fun createInstance(backend: WGPUInstanceBackend? = null): WGPU? = memScoped {
            wgpuCreateInstance(getDescriptor(backend))
                ?.let { WGPU(it) }
        }

        private fun MemScope.getDescriptor(backend: WGPUInstanceBackend?): CValue<WGPUInstanceDescriptor>? {
            if (backend == null) return null

            val descriptor = cValue<WGPUInstanceDescriptor> {
                nextInChain = cValue<WGPUInstanceExtras> {
                    chain.sType = WGPUSType_InstanceExtras
                    backends = backend
                }.ptr.reinterpret()
            }

            return descriptor
        }
    }

    override fun close() {
        wgpuInstanceRelease(handler)
    }
}