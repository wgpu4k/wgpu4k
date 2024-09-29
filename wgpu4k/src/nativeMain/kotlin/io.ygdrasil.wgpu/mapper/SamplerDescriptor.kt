@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.SamplerDescriptor
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cstr
import webgpu.native.WGPUSamplerDescriptor

internal fun ArenaBase.map(input: SamplerDescriptor) = alloc<WGPUSamplerDescriptor>().also { output ->
    if (input.label != null) output.label = input.label.cstr.getPointer(this)

    output.addressModeU = input.addressModeU.uValue
    output.addressModeV = input.addressModeV.uValue
    output.addressModeW = input.addressModeW.uValue

    output.magFilter = input.magFilter.uValue
    output.minFilter = input.minFilter.uValue
    output.mipmapFilter = input.mipmapFilter.uValue

    output.lodMinClamp = input.lodMinClamp
    output.lodMaxClamp = input.lodMaxClamp

    if (input.compare != null) output.compare = input.compare.uValue
    output.maxAnisotropy = input.maxAnisotropy.toUShort()

}
