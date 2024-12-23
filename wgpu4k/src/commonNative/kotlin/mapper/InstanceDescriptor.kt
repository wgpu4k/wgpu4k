package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.WGPUInstanceBackend
import webgpu.WGPUInstanceDescriptor
import webgpu.WGPUInstanceExtras
import webgpu.WGPUNativeSType_InstanceExtras

internal fun MemoryAllocator.map(backend: WGPUInstanceBackend) = WGPUInstanceDescriptor.allocate(this).also { output ->
    output.nextInChain = WGPUInstanceExtras.allocate(this).also { nextInChain ->
        nextInChain.backends = backend.value.toULong()
        nextInChain.chain.sType = WGPUNativeSType_InstanceExtras
    }.handler
}