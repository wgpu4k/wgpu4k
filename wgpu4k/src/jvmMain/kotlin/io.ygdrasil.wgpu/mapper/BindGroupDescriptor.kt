package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.BindGroupDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUBindGroupDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUBindGroupEntry
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

internal fun Arena.map(input: BindGroupDescriptor): MemorySegment = WGPUBindGroupDescriptor.allocate(this).also { output ->
    if(input.label != null) WGPUBindGroupDescriptor.label(output, allocateFrom(input.label))
    WGPUBindGroupDescriptor.layout(output, input.layout.handler)
    if (input.entries.isNotEmpty()) {
        WGPUBindGroupDescriptor.entryCount(output, input.entries.size.toLong())
        val entries = WGPUBindGroupEntry.allocateArray(input.entries.size.toLong(), this)
        input.entries.forEachIndexed { index, entry ->
            map(entry, WGPUBindGroupEntry.asSlice(entries, index.toLong()))
        }
        WGPUBindGroupDescriptor.entries(output, entries)

    }
}

private fun Arena.map(input: BindGroupDescriptor.BindGroupEntry, output: MemorySegment) {
    WGPUBindGroupEntry.binding(output, input.binding)

    when (val resource = input.resource) {
        is BindGroupDescriptor.BufferBinding -> {
            WGPUBindGroupEntry.size(output, resource.size)
            WGPUBindGroupEntry.offset(output, resource.offset)
            WGPUBindGroupEntry.buffer(output, resource.buffer.handler)
        }

        is BindGroupDescriptor.SamplerBinding -> WGPUBindGroupEntry.sampler(output, resource.sampler.handler)
        is BindGroupDescriptor.TextureViewBinding -> WGPUBindGroupEntry.textureView(output, resource.view.handler)
    }
}

