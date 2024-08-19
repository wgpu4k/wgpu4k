package io.ygdrasil.wgpu.internal

import io.ygdrasil.wgpu.PowerPreference
import io.ygdrasil.wgpu.WGPUInstanceBackend

object JniInterfaceV2 {
    init {
        System.loadLibrary("wgpu4kv2")
    }
    /*** Instance ***/
    external fun wgpuCreateInstance(backend: WGPUInstanceBackend?): Long
    external fun wgpuInstanceRequestAdapter(handler: Long, powerPreference: PowerPreference?, surface: Long): Long
    external fun wgpuInstanceCreateSurface(handler: Long, androidSurface: android.view.Surface): Long
    external fun wgpuInstanceRelease(handler: Long)
}