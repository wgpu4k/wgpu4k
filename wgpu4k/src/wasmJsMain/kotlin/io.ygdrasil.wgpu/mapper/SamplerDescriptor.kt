package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.SamplerDescriptor
import io.ygdrasil.webgpu.internal.js.GPUSamplerDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.js.toJsNumber

internal fun map(input: SamplerDescriptor): GPUSamplerDescriptor = createJsObject<GPUSamplerDescriptor>().apply {
    if (input.label != null) label = input.label.toJsString()
    addressModeU = input.addressModeU.value
    addressModeV = input.addressModeV.value
    addressModeW = input.addressModeW.value
    magFilter = input.magFilter.name
    minFilter = input.minFilter.name
    mipmapFilter = input.mipmapFilter.name
    lodMinClamp = input.lodMinClamp.toJsNumber()
    lodMaxClamp = input.lodMaxClamp.toJsNumber()
    if (input.compare != null) compare = input.compare.value
    maxAnisotropy = input.maxAnisotropy.toJsNumber()
}