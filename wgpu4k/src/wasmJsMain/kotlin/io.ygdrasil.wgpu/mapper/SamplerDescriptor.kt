package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.SamplerDescriptor
import io.ygdrasil.webgpu.internal.js.GPUSamplerDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.js.toJsNumber

internal fun map(input: SamplerDescriptor): GPUSamplerDescriptor = createJsObject<GPUSamplerDescriptor>().apply {
    if (input.label != null) label = input.label.toJsString()
    addressModeU = input.addressModeU.stringValue
    addressModeV = input.addressModeV.stringValue
    addressModeW = input.addressModeW.stringValue
    magFilter = input.magFilter.name
    minFilter = input.minFilter.name
    mipmapFilter = input.mipmapFilter.name
    lodMinClamp = input.lodMinClamp.toJsNumber()
    lodMaxClamp = input.lodMaxClamp.toJsNumber()
    if (input.compare != null) compare = input.compare.stringValue
    maxAnisotropy = input.maxAnisotropy.toJsNumber()
}