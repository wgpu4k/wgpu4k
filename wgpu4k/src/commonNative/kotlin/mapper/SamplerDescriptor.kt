package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.SamplerDescriptor
import webgpu.WGPUSamplerDescriptor


internal fun MemoryAllocator.map(input: SamplerDescriptor): WGPUSamplerDescriptor =
    WGPUSamplerDescriptor.allocate(this).also { output ->
        if (input.label != null) map(input.label, output.label)

        output.addressModeU = input.addressModeU.uValue
        output.addressModeV = input.addressModeV.uValue
        output.addressModeW = input.addressModeW.uValue

        output.magFilter = input.magFilter.uValue
        output.minFilter = input.minFilter.uValue
        output.mipmapFilter = input.mipmapFilter.uValue

        output.lodMinClamp = input.lodMinClamp
        output.lodMaxClamp = input.lodMaxClamp

        if (input.compare != null) output.compare = input.compare.uValue
        output.maxAnisotropy = input.maxAnisotropy

    }
