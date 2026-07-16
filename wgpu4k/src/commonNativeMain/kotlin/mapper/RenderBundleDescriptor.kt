package io.ygdrasil.webgpu.mapper

import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.webgpu.GPURenderBundleDescriptor
import io.ygdrasil.wgpu.WGPURenderBundleDescriptor

internal fun MemoryAllocator.map(input: GPURenderBundleDescriptor): WGPURenderBundleDescriptor =
    WGPURenderBundleDescriptor.allocate(this).also { output ->
        map(input.label, output.label)
    }