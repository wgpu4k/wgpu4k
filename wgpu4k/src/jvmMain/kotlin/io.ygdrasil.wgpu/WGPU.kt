package io.ygdrasil.wgpu


import io.ygdrasil.wgpu.internal.jvm.Architecture
import io.ygdrasil.wgpu.internal.jvm.Os
import io.ygdrasil.wgpu.internal.jvm.Platform
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.*
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUChainedStruct
import io.ygdrasil.wgpu.internal.jvm.panama.WGPURequestAdapterOptions
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUSurfaceDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUSurfaceDescriptorFromMetalLayer
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUSurfaceDescriptorFromWindowsHWND
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUSurfaceDescriptorFromXlibWindow
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h.WGPUSType_InstanceExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.io.InputStream
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption


class WGPU(private val handler: MemorySegment) : AutoCloseable {

    override fun close() {
        wgpu_h.wgpuInstanceRelease(handler)
    }

    fun requestAdapter(
        renderingContext: RenderingContext,
        powerPreference: PowerPreference? = null
    ): Adapter? = confined { arena ->

        val adapterState = MutableStateFlow<MemorySegment?>(null)

        val handleRequestAdapter = WGPURequestAdapterCallback.allocate({ statusAsInt, adapter, message, param4 ->
            if (statusAsInt == wgpu_h.WGPURequestAdapterStatus_Success()) {
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
        WGPURequestAdapterOptions.compatibleSurface(options, renderingContext.handler)
        if (powerPreference != null) WGPURequestAdapterOptions.powerPreference(options, powerPreference.value)

        wgpu_h.wgpuInstanceRequestAdapter(handler, options, handleRequestAdapter, MemorySegment.NULL)


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
                            WGPUChainedStruct.sType(chain, wgpu_h.WGPUSType_SurfaceDescriptorFromMetalLayer())
                        })
                    WGPUSurfaceDescriptorFromMetalLayer.layer(nextInChain, layer)
                })

            wgpu_h.wgpuInstanceCreateSurface(handler, surfaceDescriptor)
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
                            WGPUChainedStruct.sType(chain, wgpu_h.WGPUSType_SurfaceDescriptorFromXlibWindow())
                        })
                    WGPUSurfaceDescriptorFromXlibWindow.display(nextInChain, display)
                    WGPUSurfaceDescriptorFromXlibWindow.window(nextInChain, window)
                })

            wgpu_h.wgpuInstanceCreateSurface(handler, surfaceDescriptor)
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
                            WGPUChainedStruct.sType(chain, wgpu_h.WGPUSType_SurfaceDescriptorFromWindowsHWND())
                        })
                    WGPUSurfaceDescriptorFromWindowsHWND.hwnd(nextInChain, hwnd)
                    WGPUSurfaceDescriptorFromWindowsHWND.hinstance(nextInChain, hinstance)
                })

            wgpu_h.wgpuInstanceCreateSurface(handler, surfaceDescriptor)
        }
    }

    companion object {
        fun loadLibrary() {
            when (Platform.os) {
                Os.Windows -> System.loadLibrary("WGPU")
                else -> {
                    val libraryPath = findLibraryPath()
                    val libraryFile =
                        extractResourceToTemp(libraryPath)?.toFile() ?: error("fail to find webgpu library")
                    System.load(libraryFile.absolutePath)
                }
            }
        }

        private fun findLibraryPath(): String {
            val os = when (Platform.os) {
                Os.Linux -> "linux"
                Os.Windows -> "win32"
                Os.MacOs -> "darwin"
            }

            val libraryName = when (Platform.os) {
                Os.Linux -> "libWGPU.so"
                Os.Windows -> "WGPU.dll"
                Os.MacOs -> "libWGPU.dylib"
            }

            val architecture = when (Platform.architecture) {
                Architecture.X86_64 -> "x86-64"
                Architecture.AARCH64 -> when (Platform.os) {
                    Os.Windows -> error("aarch64 not supported on windows")
                    else -> "aarch64"
                }
            }

            return "/$os-$architecture/$libraryName"
        }

        private fun extractResourceToTemp(fileOnClasspath: String): Path? {
            // fetch file from the classpath
            val resourceAsStream: InputStream? = WGPU::class.java.getResourceAsStream(fileOnClasspath)

            if (resourceAsStream == null) {
                println("Could not find file $fileOnClasspath on the classpath")
                return null
            }

            // create a temp file
            val tempFile = Files.createTempFile("wgpu", "lib")
            println(tempFile.toFile().absolutePath)
            tempFile.toFile().deleteOnExit()

            // copy the file to the temp directory
            Files.copy(resourceAsStream, tempFile, StandardCopyOption.REPLACE_EXISTING)

            return tempFile
        }

        fun createInstance(backend: WGPUInstanceBackend? = null): WGPU? = confined { arena ->
            wgpu_h.wgpuCreateInstance(arena.getDescriptor(backend))
                ?.let { WGPU(it) }
        }

        private fun Arena.getDescriptor(backend: WGPUInstanceBackend?): MemorySegment {
            val descriptor = WGPUInstanceDescriptor.allocate(this)

            if (backend != null) {
                WGPUInstanceExtras.allocate(this).also { nextInChain ->
                    WGPUInstanceExtras.backends(nextInChain, backend.value)
                    WGPUInstanceExtras.chain(nextInChain).also { chain ->
                        WGPUChainedStruct.sType(nextInChain, WGPUSType_InstanceExtras())
                    }
                    WGPUInstanceDescriptor.nextInChain(descriptor, nextInChain)
                }

            }

            return descriptor
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