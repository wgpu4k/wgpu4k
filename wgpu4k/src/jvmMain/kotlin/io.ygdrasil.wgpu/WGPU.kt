package io.ygdrasil.wgpu


import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.exportAndLoadLibrary
import io.ygdrasil.wgpu.mapper.map
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import webgpu.WGPUInstance
import webgpu.wgpuInstanceRelease
import java.lang.foreign.MemorySegment


class WGPU(private val handler: WGPUInstance) : AutoCloseable {

    override fun close() {
        wgpuInstanceRelease(handler)
    }

    fun requestAdapter(
        surface: Surface,
        powerPreference: PowerPreference? = null
    ): Adapter? = confined { arena ->

        val adapterState = MutableStateFlow<MemorySegment?>(null)

        val handleRequestAdapter = WGPUInstanceRequestAdapterCallback.allocate({ statusAsInt, adapter, message, param4 ->
            if (statusAsInt == WGPURequestAdapterStatus_Success()) {
                adapterState.update { adapter }
            } else {

                println(
                    "request_adapter status=${WGPURequestAdapterStatus.of(statusAsInt)} message=${
                        message.getString(
                            0
                        )
                    }"
                )
            }
        }, arena)


        val options = WGPURequestAdapterOptions.allocate(arena)
        WGPURequestAdapterOptions.compatibleSurface(options, surface.handler)
        if (powerPreference != null) WGPURequestAdapterOptions.powerPreference(options, powerPreference.value)

        wgpuInstanceRequestAdapter(handler, options, handleRequestAdapter, MemorySegment.NULL)


        adapterState.value?.let { Adapter(it) }
    }

    fun getSurfaceFromMetalLayer(layer: MemorySegment): MemorySegment = confined { arena ->
        WGPUSurfaceDescriptor.allocate(arena).let { surfaceDescriptor ->
            WGPUSurfaceDescriptor.nextInChain(
                surfaceDescriptor,
                WGPUSurfaceDescriptorFromMetalLayer.allocate(arena).also { nextInChain ->
                    WGPUSurfaceDescriptorFromMetalLayer.chain(
                        nextInChain,
                        WGPUChainedStruct.allocate(arena).also { chain ->
                            WGPUChainedStruct.sType(chain, WGPUSType_SurfaceDescriptorFromMetalLayer())
                        })
                    WGPUSurfaceDescriptorFromMetalLayer.layer(nextInChain, layer)
                })

            wgpuInstanceCreateSurface(handler, surfaceDescriptor)
        }
    }

    fun getSurfaceFromX11Window(display: MemorySegment, window: Long): MemorySegment? = confined { arena ->
        WGPUSurfaceDescriptor.allocate(arena).let { surfaceDescriptor ->
            WGPUSurfaceDescriptor.nextInChain(
                surfaceDescriptor,
                WGPUSurfaceDescriptorFromXlibWindow.allocate(arena).also { nextInChain ->
                    WGPUSurfaceDescriptorFromXlibWindow.chain(
                        nextInChain,
                        WGPUChainedStruct.allocate(arena).also { chain ->
                            WGPUChainedStruct.sType(chain, WGPUSType_SurfaceDescriptorFromXlibWindow())
                        })
                    WGPUSurfaceDescriptorFromXlibWindow.display(nextInChain, display)
                    WGPUSurfaceDescriptorFromXlibWindow.window(nextInChain, window)
                })

            wgpuInstanceCreateSurface(handler, surfaceDescriptor)
        }
    }

    fun getSurfaceFromWindows(hinstance: MemorySegment, hwnd: MemorySegment): MemorySegment? = confined { arena ->
        WGPUSurfaceDescriptor.allocate(arena).let { surfaceDescriptor ->
            WGPUSurfaceDescriptor.nextInChain(
                surfaceDescriptor,
                WGPUSurfaceDescriptorFromWindowsHWND.allocate(arena).also { nextInChain ->
                    WGPUSurfaceDescriptorFromWindowsHWND.chain(
                        nextInChain,
                        WGPUChainedStruct.allocate(arena).also { chain ->
                            WGPUChainedStruct.sType(chain, WGPUSType_SurfaceDescriptorFromWindowsHWND())
                        })
                    WGPUSurfaceDescriptorFromWindowsHWND.hwnd(nextInChain, hwnd)
                    WGPUSurfaceDescriptorFromWindowsHWND.hinstance(nextInChain, hinstance)
                })

            wgpuInstanceCreateSurface(handler, surfaceDescriptor)
        }
    }

    companion object {

        private var libraryLoaded = false

        fun loadLibrary() {
            if (libraryLoaded) return
            libraryLoaded = true
            exportAndLoadLibrary()
        }

        fun createInstance(backend: WGPUInstanceBackend? = null): WGPU? = confined { arena ->
            backend?.let { arena.map(backend) }
                .let { wgpuCreateInstance(it ?: MemorySegment.NULL) }
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

enum class WGPURequestAdapterStatus(
    val `value`: Int,
) {
    Success(0),
    Unavailable(1),
    Error(2),
    Unknown(3);

    companion object {
        fun of(`value`: Int): WGPURequestAdapterStatus? = entries.find {
            it.value == value
        }
    }
}