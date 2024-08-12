package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JniInterface

class WGPU(public val handler: Long) : AutoCloseable {

    override fun close() {
        JniInterface.instance.wgpuInstanceRelease(handler)
    }

    fun requestAdapter(
        surface: Surface,
        powerPreference: PowerPreference? = null
    ): Adapter {

        JniInterface.instance.wgpuInstanceRequestAdapter(handler, null, null, null)

        return Adapter(0L)
    }
    
    companion object {
        
        fun createInstance(backend: WGPUInstanceBackend? = null): WGPU {
            return JniInterface.instance.wgpuCreateInstance(backend)
                .let { WGPU(it) }
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
