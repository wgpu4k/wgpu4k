package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.BindGroupLayout
import io.ygdrasil.webgpu.Buffer
import io.ygdrasil.webgpu.GPUBindGroupDescriptor
import io.ygdrasil.webgpu.GPUBindGroupEntry
import io.ygdrasil.webgpu.GPUBufferBinding
import io.ygdrasil.webgpu.GPUSampler
import io.ygdrasil.webgpu.GPUTextureView
import io.ygdrasil.webgpu.Sampler
import io.ygdrasil.webgpu.TextureView
import io.ygdrasil.wgpu.WGPUBindGroupDescriptor
import io.ygdrasil.wgpu.WGPUBindGroupEntry

internal fun MemoryAllocator.map(input: GPUBindGroupDescriptor): WGPUBindGroupDescriptor =
    WGPUBindGroupDescriptor.allocate(this).also { output ->
        map(input.label, output.label)
        output.layout = (input.layout as BindGroupLayout).handler
        if (input.entries.isNotEmpty()) {
            output.entryCount = input.entries.size.toULong()
            output.entries = WGPUBindGroupEntry.allocateArray(this, input.entries.size.toUInt()) { index, entry ->
                map(input.entries[index.toInt()], entry)
            }
        }
    }

private fun MemoryAllocator.map(input: GPUBindGroupEntry, output: WGPUBindGroupEntry) {
    output.binding = input.binding

    when (val resource = input.resource) {
        is GPUBufferBinding -> {
            output.size = resource.size ?: resource.buffer.size
            output.offset = resource.offset
            output.buffer = (resource.buffer as Buffer).handler
        }

        is GPUSampler -> output.sampler = (resource as Sampler).handler
        is GPUTextureView -> output.textureView = (resource as TextureView).handler
    }
}

