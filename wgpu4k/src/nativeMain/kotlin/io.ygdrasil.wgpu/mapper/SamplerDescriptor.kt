@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.SamplerDescriptor
import kotlinx.cinterop.*
import webgpu.*

internal fun Arena.map(input: SamplerDescriptor) = alloc<WGPUSamplerDescriptor>().also { output ->
    if (input.label != null) WGPUSamplerDescriptor.label(output, allocateFrom(input.label))

    WGPUSamplerDescriptor.addressModeU(output, input.addressModeU.value)
    WGPUSamplerDescriptor.addressModeV(output, input.addressModeV.value)
    WGPUSamplerDescriptor.addressModeW(output, input.addressModeW.value)

    WGPUSamplerDescriptor.magFilter(output, input.magFilter.value)
    WGPUSamplerDescriptor.minFilter(output, input.minFilter.value)
    WGPUSamplerDescriptor.mipmapFilter(output, input.mipmapFilter.value)

    WGPUSamplerDescriptor.lodMinClamp(output, input.lodMinClamp)
    WGPUSamplerDescriptor.lodMaxClamp(output, input.lodMaxClamp)

    if (input.compare != null) WGPUSamplerDescriptor.compare(output, input.compare.value)
    WGPUSamplerDescriptor.maxAnisotropy(output, input.maxAnisotropy.toShort())

}
