package io.ygdrasil.wgpu.internal

import io.ygdrasil.wgpu.DeviceDescriptor
import io.ygdrasil.wgpu.PowerPreference

internal object JniInterface {
    init {
        System.loadLibrary("wgpu4kv2")
    }

    /*** Instance ***/
    external fun wgpuInstanceRequestAdapter(handler: Long, powerPreference: PowerPreference?, surface: Long): Long

    /*** Adapter ***/
    external fun wgpuAdapterRequestDevice(handler: Long, descriptor: DeviceDescriptor): Long

    /*** Surface ***/
    external fun wgpuSurfaceGetCurrentTexture(handler: Long): Long
    external fun wgpuSurfaceConfigure(
        handler: Long,
        device: Long,
        usage: Int,
        format: Int,
        alphaMode: Int,
        width: Int,
        height: Int
    )

}