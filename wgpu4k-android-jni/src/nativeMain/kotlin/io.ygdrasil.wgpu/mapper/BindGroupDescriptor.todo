@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.BindGroupDescriptor
import kotlinx.cinterop.*
import webgpu.WGPUBindGroupDescriptor
import webgpu.WGPUBindGroupEntry


internal fun ArenaBase.map(input: BindGroupDescriptor) = alloc<WGPUBindGroupDescriptor>().also { output ->
    if (input.label != null) output.label = input.label.cstr.getPointer(this@map)
    output.layout = input.layout.handler
    if (input.entries.isNotEmpty()) {
        output.entryCount = input.entries.size.toULong()
        val entries = allocArray<WGPUBindGroupEntry>(input.entries.size)
        input.entries.forEachIndexed { index, entry ->
            map(entry, entries[index])
        }
        output.entries = entries
    }
}

private fun map(input: BindGroupDescriptor.BindGroupEntry, output: WGPUBindGroupEntry) {
    output.binding = input.binding.toUInt()

    when (val resource = input.resource) {
        is BindGroupDescriptor.BufferBinding -> {
            output.size = resource.size.toULong()
            output.offset = resource.offset.toULong()
            output.buffer = resource.buffer.handler
        }

        is BindGroupDescriptor.SamplerBinding -> output.sampler = resource.sampler.handler
        is BindGroupDescriptor.TextureViewBinding -> output.textureView = resource.view.handler
    }
}

