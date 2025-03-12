package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.BindGroupDescriptor
import io.ygdrasil.webgpu.internal.js.GPUBindGroupDescriptor
import io.ygdrasil.webgpu.internal.js.GPUBindGroupEntry
import io.ygdrasil.webgpu.internal.js.GPUBufferBinding
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: BindGroupDescriptor): GPUBindGroupDescriptor = createJsObject<GPUBindGroupDescriptor>().apply {
    if (input.label != null) label = input.label
    layout = input.layout.handler
    entries = input.entries.map { map(it) }.toTypedArray()
}

private fun map(input: BindGroupEntry): GPUBindGroupEntry =
    createJsObject<GPUBindGroupEntry>().apply {
        binding = input.binding
        resource = when (val localResource = input.resource) {
            is SamplerBinding -> localResource.sampler.handler
            is BufferBinding -> createJsObject<GPUBufferBinding>().apply {
                buffer = localResource.buffer.handler
                offset = localResource.offset
                size = localResource.size
            }

            is TextureViewBinding -> localResource.view.handler
        }
    }
