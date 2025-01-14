package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.SamplerDescriptor
import io.ygdrasil.webgpu.internal.js.GPUSamplerDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: SamplerDescriptor): GPUSamplerDescriptor = createJsObject<GPUSamplerDescriptor>().apply {
    if (input.label != null) label = input.label
    addressModeU = input.addressModeU.value
    addressModeV = input.addressModeV.value
    addressModeW = input.addressModeW.value
    magFilter = input.magFilter.value
    minFilter = input.minFilter.value
    mipmapFilter = input.mipmapFilter.value
    lodMinClamp = input.lodMinClamp
    lodMaxClamp = input.lodMaxClamp
    if (input.compare != null) compare = input.compare.value
    maxAnisotropy = input.maxAnisotropy.toShort()
}