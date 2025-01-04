package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.BindGroupDescriptor
import io.ygdrasil.wgpu.WGPUBindGroupDescriptor
import io.ygdrasil.wgpu.WGPUBindGroupEntry

internal fun MemoryAllocator.map(input: BindGroupDescriptor): WGPUBindGroupDescriptor =
    WGPUBindGroupDescriptor.allocate(this).also { output ->
        if (input.label != null) output.label = allocateFrom(input.label)
        output.layout = input.layout.handler
        if (input.entries.isNotEmpty()) {
            output.entryCount = input.entries.size.toULong()
            output.entries = WGPUBindGroupEntry.allocateArray(this, input.entries.size.toUInt()) { index, entry ->
                map(input.entries[index.toInt()], entry)
            }
        }
    }

private fun MemoryAllocator.map(input: BindGroupDescriptor.BindGroupEntry, output: WGPUBindGroupEntry) {
    output.binding = input.binding

    when (val resource = input.resource) {
        is BindGroupDescriptor.BufferBinding -> {
            output.size = resource.size
            output.offset = resource.offset
            output.buffer = resource.buffer.handler
        }

        is BindGroupDescriptor.SamplerBinding -> output.sampler = resource.sampler.handler
        is BindGroupDescriptor.TextureViewBinding -> output.textureView = resource.view.handler
    }
}

