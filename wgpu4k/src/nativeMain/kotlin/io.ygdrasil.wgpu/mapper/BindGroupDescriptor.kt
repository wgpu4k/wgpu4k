@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.BindGroupDescriptor
import kotlinx.cinterop.*
import webgpu.*


internal fun Arena.map(input: BindGroupDescriptor) = alloc<WGPUBindGroupDescriptor> {
    if (input.label != null) label = input.label.cstr.getPointer(this@map)
    layout = input.layout.handler
    if (input.entries.isNotEmpty()) {
        entryCount = input.entries.size.toULong()
        entries = this@map.allocArray<WGPUBindGroupEntry>(input.entries.size)
        input.entries.forEachIndexed { index, entry ->
            map(entry, entries!![index])
        }
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

