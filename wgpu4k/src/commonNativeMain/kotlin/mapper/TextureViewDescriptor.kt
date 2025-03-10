package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUTextureViewDescriptor
import io.ygdrasil.wgpu.WGPUTextureViewDescriptor

internal fun MemoryAllocator.map(input: GPUTextureViewDescriptor) = WGPUTextureViewDescriptor.allocate(this)
    .also { output ->

        map(input.label, output.label)
        input.format?.let { output.format = it.value.toUInt() }
        input.dimension?.let { output.dimension = it.value.toUInt() }
        output.aspect = input.aspect.value.toUInt()
        output.baseMipLevel = input.baseMipLevel.toUInt()
        input.mipLevelCount?.let { output.mipLevelCount = it.toUInt() }
        output.baseArrayLayer = input.baseArrayLayer.toUInt()
        input.arrayLayerCount?.let { output.arrayLayerCount = it.toUInt() }
    }

