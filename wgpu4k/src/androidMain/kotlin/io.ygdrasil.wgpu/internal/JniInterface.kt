package io.ygdrasil.wgpu.internal

import io.ygdrasil.wgpu.DeviceDescriptor

internal object JniInterface {
    init {
        System.loadLibrary("wgpu4kv2")
    }

    /*** Adapter ***/
    external fun wgpuAdapterRequestDevice(handler: Long, descriptor: DeviceDescriptor): Long

}