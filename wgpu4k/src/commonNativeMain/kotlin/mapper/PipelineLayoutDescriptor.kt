package io.ygdrasil.webgpu.mapper

import io.ygdrasil.kffi.MemoryAllocator
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
                .handler
        }
    }
