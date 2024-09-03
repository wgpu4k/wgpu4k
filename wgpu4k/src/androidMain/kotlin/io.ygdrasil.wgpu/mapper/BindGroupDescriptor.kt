package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.BindGroupDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUBindGroupDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUBindGroupEntry
import io.ygdrasil.wgpu.internal.toAddress
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: BindGroupDescriptor): Long =
    WGPUBindGroupDescriptor.allocate(this).also { output ->
    if(input.label != null) WGPUBindGroupDescriptor.label(output, allocateFrom(input.label))
    WGPUBindGroupDescriptor.layout(output, input.layout.mhandler)
    if (input.entries.isNotEmpty()) {
        WGPUBindGroupDescriptor.entryCount(output, input.entries.size.toLong())
        val entries = WGPUBindGroupEntry.allocateArray(input.entries.size.toLong(), this)
        input.entries.forEachIndexed { index, entry ->
            map(entry, WGPUBindGroupEntry.asSlice(entries, index.toLong()))
        }
        WGPUBindGroupDescriptor.entries(output, entries)

    }
    }.pointer.toAddress()

private fun SegmentAllocator.map(input: BindGroupDescriptor.BindGroupEntry, output: MemorySegment) {
    WGPUBindGroupEntry.binding(output, input.binding)

    when (val resource = input.resource) {
        is BindGroupDescriptor.BufferBinding -> {
            WGPUBindGroupEntry.size(output, resource.size)
            WGPUBindGroupEntry.offset(output, resource.offset)
            WGPUBindGroupEntry.buffer(output, resource.buffer.mhandler)
        }

        is BindGroupDescriptor.SamplerBinding -> WGPUBindGroupEntry.sampler(output, resource.sampler.mhandler)
        is BindGroupDescriptor.TextureViewBinding -> WGPUBindGroupEntry.textureView(output, resource.view.mhandler)
    }
}

