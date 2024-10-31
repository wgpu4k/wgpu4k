package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.BindGroupDescriptor
import webgpu.WGPUBindGroupDescriptor
import webgpu.WGPUBindGroupEntry

internal fun MemoryAllocator.map(input: BindGroupDescriptor): WGPUBindGroupDescriptor = WGPUBindGroupDescriptor.allocate(this).also { output ->
    if(input.label != null) map(input.label, output.label)
    output.layout = input.layout.handler
    if (input.entries.isNotEmpty()) {
        output.entryCount = input.entries.size.toULong()
        val entries = WGPUBindGroupEntry.allocateArray(input.entries.size.toLong(), this)
        input.entries.forEachIndexed { index, entry ->
            map(entry, WGPUBindGroupEntry.asSlice(entries, index.toLong()))
        }
        output.entries = entries

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
        is BindGroupDescriptor.TextureViewBinding -> output.textureView = resource.view.handler.handler.handler
    }
}

