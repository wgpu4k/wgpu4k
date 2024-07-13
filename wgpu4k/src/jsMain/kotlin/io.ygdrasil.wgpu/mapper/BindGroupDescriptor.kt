package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.BindGroupDescriptor
import io.ygdrasil.wgpu.internal.js.GPUBindGroupDescriptor
import io.ygdrasil.wgpu.internal.js.GPUBindGroupEntry
import io.ygdrasil.wgpu.internal.js.GPUBufferBinding
import io.ygdrasil.wgpu.internal.js.createJsObject

internal fun map(input: BindGroupDescriptor): GPUBindGroupDescriptor = createJsObject<GPUBindGroupDescriptor>().apply {
    if (input.label != null) label = input.label
    layout = input.layout.handler
    entries = input.entries.map { map(it) }.toTypedArray()
}

private fun map(input: BindGroupDescriptor.BindGroupEntry): GPUBindGroupEntry =
    createJsObject<GPUBindGroupEntry>().apply {
        binding = input.binding
        resource = when (val localResource = input.resource) {
            is BindGroupDescriptor.SamplerBinding -> localResource.sampler.handler
            is BindGroupDescriptor.BufferBinding -> createJsObject<GPUBufferBinding>().apply {
                buffer = localResource.buffer.handler
                offset = localResource.offset
                size = localResource.size
            }

            is BindGroupDescriptor.TextureViewBinding -> localResource.view.handler
        }
    }
