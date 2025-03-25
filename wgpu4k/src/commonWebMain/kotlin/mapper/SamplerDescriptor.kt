package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUSamplerDescriptor
import io.ygdrasil.webgpu.WGPUSamplerDescriptor
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.createJsObject

internal fun map(input: GPUSamplerDescriptor): WGPUSamplerDescriptor = createJsObject<WGPUSamplerDescriptor>().apply {
    label = input.label
    addressModeU = input.addressModeU.value
    addressModeV = input.addressModeV.value
    addressModeW = input.addressModeW.value
    magFilter = input.magFilter.value
    minFilter = input.minFilter.value
    mipmapFilter = input.mipmapFilter.value
    lodMinClamp = input.lodMinClamp.asJsNumber()
    lodMaxClamp = input.lodMaxClamp.asJsNumber()
    input.compare?.let { compare = it.value }
    maxAnisotropy = input.maxAnisotropy.asJsNumber()
}