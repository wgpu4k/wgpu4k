package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.RenderBundleDescriptor
import webgpu.WGPURenderBundleDescriptor

internal fun MemoryAllocator.map(input: RenderBundleDescriptor): WGPURenderBundleDescriptor = WGPURenderBundleDescriptor.allocate(this).also { output ->
    if (input.label != null) map(input.label, output.label)
}