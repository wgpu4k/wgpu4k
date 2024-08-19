package io.ygdrasil.wgpu.internal

import io.ygdrasil.wgpu.WGPUInstanceBackend

object JniInterfaceV2 {
    init {
        System.loadLibrary("wgpu4kv2")
    }

    external fun wgpuCreateInstance(backend: WGPUInstanceBackend?): Long
}