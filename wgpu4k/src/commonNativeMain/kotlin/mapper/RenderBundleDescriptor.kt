package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.RenderBundleDescriptor
import io.ygdrasil.wgpu.WGPURenderBundleDescriptor

internal fun MemoryAllocator.map(input: RenderBundleDescriptor): WGPURenderBundleDescriptor =
    WGPURenderBundleDescriptor.allocate(this).also { output ->
        if (input.label != null) output.label = allocateFrom(input.label)
    }