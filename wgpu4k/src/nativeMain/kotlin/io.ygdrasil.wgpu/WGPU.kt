@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu

import kotlinx.cinterop.*
import webgpu.*

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

        val handleRequestAdapter: WGPURequestAdapterCallback =
            staticCFunction<WGPURequestAdapterStatus, WGPUAdapter, CPointer<ByteVar>?, COpaquePointer, Unit> { status, adapter, message, userData ->
                println("WGPURequestAdapterCallback ${userData} ${adapter}")
                if (status == WGPURequestAdapterStatus_Success) {
                    lastFindAdapter = adapter
                } else {
                    println("request_adapter status=$status message=${message?.toKStringFromUtf8()}\n")
                }

            }.reinterpret()

        wgpuInstanceRequestAdapter(handler, options, handleRequestAdapter, null)

        return lastFindAdapter?.let { Adapter(it) }
    }

    fun getSurfaceFromWindows(hinstance: MemorySegment, hwnd: MemorySegment): MemorySegment? = confined { arena ->
        WGPUSurfaceDescriptor.allocate(arena).let { surfaceDescriptor ->
            WGPUSurfaceDescriptor.nextInChain(
                surfaceDescriptor,
                WGPUSurfaceDescriptorFromWindowsHWND.allocate(arena).also { nextInChain ->
                    WGPUSurfaceDescriptorFromWindowsHWND.chain(
                        nextInChain,
                        WGPUChainedStruct.allocate(arena).also { chain ->
                            WGPUChainedStruct.sType(chain, wgpu_h.WGPUSType_SurfaceDescriptorFromWindowsHWND())
                        })
                    WGPUSurfaceDescriptorFromWindowsHWND.hwnd(nextInChain, hwnd)
                    WGPUSurfaceDescriptorFromWindowsHWND.hinstance(nextInChain, hinstance)
                })

            wgpu_h.wgpuInstanceCreateSurface(handler, surfaceDescriptor)
        }
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