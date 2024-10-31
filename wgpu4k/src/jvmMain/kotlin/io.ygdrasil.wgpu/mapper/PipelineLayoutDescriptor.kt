package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.PipelineLayoutDescriptor
import io.ygdrasil.wgpu.internal.jvm.toPointerArray
import webgpu.WGPUPipelineLayoutDescriptor

internal fun MemoryAllocator.map(input: PipelineLayoutDescriptor): WGPUPipelineLayoutDescriptor = WGPUPipelineLayoutDescriptor.allocate(this).also { output ->
    if (input.label != null) map(input.label, output.label)
    if (input.bindGroupLayouts.isNotEmpty()) {
        output.bindGroupLayoutCount = input.bindGroupLayouts.size.toULong()
        output.bindGroupLayouts= input.bindGroupLayouts.map { it.handler }.toPointerArray(this)
    }
}

