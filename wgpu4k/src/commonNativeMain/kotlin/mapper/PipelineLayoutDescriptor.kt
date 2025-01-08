package io.ygdrasil.webgpu.mapper

import ffi.ArrayHolder
import ffi.MemoryAllocator
import io.ygdrasil.webgpu.PipelineLayoutDescriptor
import io.ygdrasil.wgpu.WGPUPipelineLayoutDescriptor

internal fun MemoryAllocator.map(input: PipelineLayoutDescriptor): WGPUPipelineLayoutDescriptor =
    WGPUPipelineLayoutDescriptor.allocate(this).also { output ->
        if (input.label != null) output.label = allocateFrom(input.label)
        if (input.bindGroupLayouts.isNotEmpty()) {
            output.bindGroupLayoutCount = input.bindGroupLayouts.size.toULong()
            output.bindGroupLayouts = input.bindGroupLayouts.map { it.handler.handler }
                .let { bufferOfAddresses(it) }
                .let { ArrayHolder(it.handler) }
        }
    }

