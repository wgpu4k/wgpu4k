package io.ygdrasil.wgpu.internal

import io.ygdrasil.wgpu.WGPUInstanceBackend

class JniInterface  {
    init {
        System.loadLibrary("wgpu4k")
    }

    external fun createWgpuInstance2(): Long

    /*** Instance ***/
    external fun wgpuInstanceRelease(handler: Long)
    external fun wgpuInstanceRequestAdapter(
        handler: Long,
        nothing: Any?,
        nothing1: Any?,
        nothing2: Any?,
    )
    external fun wgpuCreateInstance(backend: WGPUInstanceBackend?): Long

    /*** TextureView ***/
    external fun wgpuTextureViewRelease(handler: Long)

    companion object {

        val instance by lazy { JniInterface() }

    }
}