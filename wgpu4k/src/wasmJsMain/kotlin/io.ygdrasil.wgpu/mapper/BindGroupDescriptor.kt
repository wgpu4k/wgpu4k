package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.BindGroupDescriptor
import io.ygdrasil.wgpu.internal.js.*

internal fun map(input: BindGroupDescriptor): GPUBindGroupDescriptor {
    return createJsObject<GPUBindGroupDescriptor>().apply {
        if (input.label != null) label = input.label.toJsString()
        layout = input.layout.handler
        entries = input.entries.map { map(it) }.toJsArray()
    }
}

private fun map(input: BindGroupDescriptor.BindGroupEntry): GPUBindGroupEntry =
    createJsObject<GPUBindGroupEntry>().apply {
        binding = input.binding
        resource = when (val localResource = input.resource) {
            is BindGroupDescriptor.SamplerBinding -> localResource.sampler.handler
            is BindGroupDescriptor.BufferBinding -> createJsObject<GPUBufferBinding>().apply {
                buffer = localResource.buffer.handler
                offset = localResource.offset.toJsNumber()
                size = localResource.size.toJsNumber()
            }

            is BindGroupDescriptor.TextureViewBinding -> localResource.view.handler
        }
    }
