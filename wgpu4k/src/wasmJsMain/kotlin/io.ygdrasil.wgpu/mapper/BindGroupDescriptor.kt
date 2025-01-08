package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.BindGroupDescriptor
import io.ygdrasil.webgpu.internal.js.GPUBindGroupDescriptor
import io.ygdrasil.webgpu.internal.js.GPUBindGroupEntry
import io.ygdrasil.webgpu.internal.js.GPUBufferBinding
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.js.mapJsArray
import io.ygdrasil.webgpu.internal.js.toJsNumber

internal fun map(input: BindGroupDescriptor): GPUBindGroupDescriptor {
    return createJsObject<GPUBindGroupDescriptor>().apply {
        if (input.label != null) label = input.label.toJsString()
        layout = input.layout.handler
        entries = input.entries.mapJsArray { map(it) }
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
