package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.WGPUInstanceBackend
import webgpu.WGPUInstanceDescriptor

internal fun MemoryAllocator.map(backend: WGPUInstanceBackend) = WGPUInstanceDescriptor.allocate(this).also { output ->
    WGPUInstanceExtras.allocate(this).also { nextInChain ->
        WGPUInstanceExtras.backends(nextInChain, backend.value)
        WGPUInstanceExtras.chain(nextInChain).also { chain ->
            nextInChain.sType = WGPUSType_InstanceExtras()
        }
        output.nextInChain = nextInChain
    }
}