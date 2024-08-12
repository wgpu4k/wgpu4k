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

enum class WGPUInstanceBackend(val myvalue: Int) {

    All(0x00000000),
    Vulkan(1),
    GL(2),
    Metal(4),
    DX12(8),
    DX11(16),
    BrowserWebGPU(32),
    Primary(Vulkan.value or Metal.value or DX12.value or BrowserWebGPU.value),
    Secondary(GL.value or DX11.value);

    val value: Int
        get() {
            println("hey there $myvalue")
            return myvalue
        }
}
