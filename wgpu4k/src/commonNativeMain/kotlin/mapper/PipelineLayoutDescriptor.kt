package io.ygdrasil.webgpu.mapper

import ffi.ArrayHolder
import ffi.MemoryAllocator
import io.ygdrasil.webgpu.BindGroupLayout
import io.ygdrasil.webgpu.GPUPipelineLayoutDescriptor
import io.ygdrasil.wgpu.WGPUPipelineLayoutDescriptor

internal fun MemoryAllocator.map(input: GPUPipelineLayoutDescriptor): WGPUPipelineLayoutDescriptor =
    WGPUPipelineLayoutDescriptor.allocate(this).also { output ->
        map(input.label, output.label)
        if (input.bindGroupLayouts.isNotEmpty()) {
            output.bindGroupLayoutCount = input.bindGroupLayouts.size.toULong()
            output.bindGroupLayouts = input.bindGroupLayouts
                .map { (it as BindGroupLayout).handler.handler }
                .let { bufferOfAddresses(it) }
                .let { ArrayHolder(it.handler) }
        }
    }

