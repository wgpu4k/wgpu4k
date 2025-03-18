package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.BindGroupLayout
import io.ygdrasil.webgpu.Buffer
import io.ygdrasil.webgpu.GPUBindGroupDescriptor
import io.ygdrasil.webgpu.GPUBindGroupEntry
import io.ygdrasil.webgpu.GPUBufferBinding
import io.ygdrasil.webgpu.GPUSampler
import io.ygdrasil.webgpu.GPUTextureView
import io.ygdrasil.webgpu.Sampler
import io.ygdrasil.webgpu.TextureView
import io.ygdrasil.webgpu.WGPUBindGroupDescriptor
import io.ygdrasil.webgpu.WGPUBindGroupEntry
import io.ygdrasil.webgpu.WGPUBufferBinding
import io.ygdrasil.webgpu.createJsObject
import io.ygdrasil.webgpu.mapJsArray

internal fun map(input: GPUBindGroupDescriptor): WGPUBindGroupDescriptor = createJsObject<WGPUBindGroupDescriptor>().apply {
    label = input.label
    layout = (input.layout as BindGroupLayout).handler
    entries = input.entries.mapJsArray {
        val entry: WGPUBindGroupEntry = map(it)
        entry
    }
}

private fun map(input: GPUBindGroupEntry): WGPUBindGroupEntry =
    createJsObject<WGPUBindGroupEntry>().apply {
        binding = input.binding
        resource = when (val localResource = input.resource) {
            is GPUSampler -> (localResource as Sampler).handler
            is GPUBufferBinding -> createJsObject<WGPUBufferBinding>().apply {
                buffer = (localResource.buffer as Buffer).handler
                offset = localResource.offset
                size = localResource.size
            }

            is GPUTextureView -> (localResource as TextureView).handler
        }
    }
