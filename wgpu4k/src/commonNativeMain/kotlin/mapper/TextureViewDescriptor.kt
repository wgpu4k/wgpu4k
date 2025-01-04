package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.TextureViewDescriptor
import io.ygdrasil.wgpu.WGPUTextureViewDescriptor

internal fun MemoryAllocator.map(input: TextureViewDescriptor) = WGPUTextureViewDescriptor.allocate(this)
    .also { output ->

        if (input.label != null) output.label = allocateFrom(input.label)
        if (input.format != null) output.format = input.format.value.toUInt()
        if (input.dimension != null) output.dimension = input.dimension.value.toUInt()
        output.aspect = input.aspect.value.toUInt()
        output.baseMipLevel = input.baseMipLevel.toUInt()
        output.mipLevelCount = input.mipLevelCount.toUInt()
        output.baseArrayLayer = input.baseArrayLayer.toUInt()
        output.arrayLayerCount = input.arrayLayerCount.toUInt()
    }

