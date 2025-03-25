package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.WGPUInstanceBackend
import io.ygdrasil.wgpu.WGPUInstanceDescriptor
import io.ygdrasil.wgpu.WGPUInstanceExtras
import io.ygdrasil.wgpu.WGPUNativeSType_InstanceExtras

internal fun MemoryAllocator.map(backend: WGPUInstanceBackend) = WGPUInstanceDescriptor.allocate(this).also { output ->
    output.nextInChain = WGPUInstanceExtras.allocate(this).also { nextInChain ->
        nextInChain.backends = backend.value.toULong()
        nextInChain.chain.sType = WGPUNativeSType_InstanceExtras
    }.handler
}