package io.ygdrasil.wgpu

import android.view.SurfaceHolder
import io.ygdrasil.wgpu.internal.JniInterface

class WGPU(val handler: Long) : AutoCloseable {

    override fun close() {
        JniInterface.wgpuInstanceRelease(handler)
    }

    fun requestAdapter(
        surface: Surface,
        powerPreference: PowerPreference? = null
    ): Adapter {
        return JniInterface.wgpuInstanceRequestAdapter(handler, powerPreference, surface.handler)
            .takeIf { it != 0L }
            ?.let(::Adapter) ?: error("fail to create adapter")
    }

    fun getSurface(surfaceHolder: SurfaceHolder, width: Int, height: Int): Surface {
        return JniInterface.wgpuInstanceCreateSurface(handler, surfaceHolder.surface)
            .let { Surface(it, width, height) }
    }

    companion object {
        
        fun createInstance(backend: WGPUInstanceBackend? = null): WGPU {
            return JniInterface.wgpuCreateInstance(backend)
                .let { WGPU(it) }
        }

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
