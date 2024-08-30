package io.ygdrasil.wgpu

import android.view.SurfaceHolder
import io.ygdrasil.wgpu.internal.JnaInterface
import io.ygdrasil.wgpu.internal.JniInterface
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.mapper.map

class WGPU(val handler: Long) : AutoCloseable {

    fun requestAdapter(
        surface: Surface,
        powerPreference: PowerPreference? = null
    ): Adapter {
        return JniInterface.wgpuInstanceRequestAdapter(handler, powerPreference, surface.handler)
            .takeIf { it != 0L }
            ?.let(::Adapter) ?: error("fail to create adapter")
    }

    fun getSurface(surfaceHolder: SurfaceHolder, width: Int, height: Int): Surface {
        surfaceHolder.surface
        return JniInterface.wgpuInstanceCreateSurface(handler, surfaceHolder.surface)
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
