package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.SamplerDescriptor
import io.ygdrasil.webgpu.internal.js.GPUSamplerDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: SamplerDescriptor): GPUSamplerDescriptor = createJsObject<GPUSamplerDescriptor>().apply {
    if (input.label != null) label = input.label
    addressModeU = input.addressModeU.stringValue
    addressModeV = input.addressModeV.stringValue
    addressModeW = input.addressModeW.stringValue
    magFilter = input.magFilter.name
    minFilter = input.minFilter.name
    mipmapFilter = input.mipmapFilter.name
    lodMinClamp = input.lodMinClamp
    lodMaxClamp = input.lodMaxClamp
    if (input.compare != null) compare = input.compare.stringValue
    maxAnisotropy = input.maxAnisotropy.toShort()
}