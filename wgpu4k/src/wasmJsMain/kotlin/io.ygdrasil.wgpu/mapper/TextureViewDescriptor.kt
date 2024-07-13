package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.TextureViewDescriptor
import io.ygdrasil.wgpu.internal.js.GPUTextureViewDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject

internal fun map(input: TextureViewDescriptor): GPUTextureViewDescriptor =
    createJsObject<GPUTextureViewDescriptor>().apply {
        if (input.label != null) label = input.label.toJsString()
        if (input.format?.name != null) format = input.format.name
        if (input.dimension?.stringValue != null) dimension = input.dimension.stringValue
        aspect = input.aspect.name
        baseMipLevel = input.baseMipLevel
        mipLevelCount = input.mipLevelCount
        baseArrayLayer = input.baseArrayLayer
        arrayLayerCount = input.arrayLayerCount
    }