package io.ygdrasil.wgpu.internal

import io.ygdrasil.wgpu.DeviceDescriptor

internal object JniInterface {
    init {
        System.loadLibrary("wgpu4kv2")
    }

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