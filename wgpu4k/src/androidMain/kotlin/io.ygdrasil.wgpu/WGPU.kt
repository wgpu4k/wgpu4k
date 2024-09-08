package io.ygdrasil.wgpu

import android.view.SurfaceHolder
import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.JnaInterface
import io.ygdrasil.wgpu.internal.jna.WGPUChainedStruct
import io.ygdrasil.wgpu.internal.jna.WGPURequestAdapterOptions
import io.ygdrasil.wgpu.internal.jna.WGPUSurfaceDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUSurfaceDescriptorFromAndroidNativeWindow
import io.ygdrasil.wgpu.internal.jna.wgpu_h.WGPUSType_SurfaceDescriptorFromAndroidNativeWindow
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.mapper.map
import java.lang.foreign.MemorySegment

class WGPU(val handler: Long) : AutoCloseable {

    fun requestAdapter(
        surface: Surface,
        powerPreference: PowerPreference? = null
    ): Adapter = scoped { arena ->
        val descriptor = WGPURequestAdapterOptions.allocate(arena).also { descriptor ->
            WGPURequestAdapterOptions.powerPreference(descriptor, powerPreference?.value ?: 0)
            WGPURequestAdapterOptions.compatibleSurface(descriptor, MemorySegment(Pointer(surface.handler), Long.SIZE_BYTES.toLong()))
        }
        JnaInterface.wgpuInstanceRequestAdapterNoCallback(handler, descriptor.pointer.toAddress())
            .takeIf { it != 0L }
            ?.let(::Adapter) ?: error("fail to create adapter")
    }

    fun getSurface(surfaceHolder: SurfaceHolder, width: Int, height: Int): Surface = scoped { arena ->
        val nativeWindow = io.ygdrasil.nativeHelper.JniInterface.nativeWindowFromSurface(surfaceHolder.surface)
            .let { MemorySegment(Pointer(it), Long.SIZE_BYTES.toLong()) }

        val descriptor = WGPUSurfaceDescriptor.allocate(arena).also {descriptor ->
            WGPUSurfaceDescriptor.nextInChain(descriptor,
                WGPUSurfaceDescriptorFromAndroidNativeWindow.allocate(arena).also { nextInChain ->
                    WGPUSurfaceDescriptorFromAndroidNativeWindow.chain(nextInChain).also { chain ->
                        WGPUChainedStruct.sType(chain, WGPUSType_SurfaceDescriptorFromAndroidNativeWindow())
                    }
                    WGPUSurfaceDescriptorFromAndroidNativeWindow.window(nextInChain, nativeWindow)
                }
            )
        }

        JnaInterface.wgpuInstanceCreateSurface(handler, descriptor.pointer.toAddress())
            .let { Surface(it, width, height) }
    }

    companion object {

        fun createInstance(backend: WGPUInstanceBackend? = null): WGPU = scoped { arena ->
            backend?.let { arena.map(backend) }
                .let { JnaInterface.wgpuCreateInstance( it ?: 0L) }
                .let { WGPU(it) }
        }

    }

    override fun close() {
        JnaInterface.wgpuInstanceRelease(handler)
    }
}

enum class WGPUInstanceBackend(val value: Int) {

    All(0x00000000),
    Vulkan(1),
    GL(2),
    Metal(4),
    DX12(8),
    DX11(16),
    BrowserWebGPU(32),
    Primary(Vulkan.value or Metal.value or DX12.value or BrowserWebGPU.value),
    Secondary(GL.value or DX11.value);

}
