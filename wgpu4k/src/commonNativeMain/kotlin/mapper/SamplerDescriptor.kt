package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.SamplerDescriptor
import io.ygdrasil.wgpu.WGPUSamplerDescriptor


internal fun MemoryAllocator.map(input: SamplerDescriptor): WGPUSamplerDescriptor =
    WGPUSamplerDescriptor.allocate(this).also { output ->
        if (input.label != null) map(input.label, output.label)

        output.addressModeU = input.addressModeU.value
        output.addressModeV = input.addressModeV.value
        output.addressModeW = input.addressModeW.value

        output.magFilter = input.magFilter.value
        output.minFilter = input.minFilter.value
        output.mipmapFilter = input.mipmapFilter.value

        output.lodMinClamp = input.lodMinClamp
        output.lodMaxClamp = input.lodMaxClamp

        if (input.compare != null) output.compare = input.compare.value
        output.maxAnisotropy = input.maxAnisotropy

    }
