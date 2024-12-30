package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.TextureViewDescriptor
import io.ygdrasil.webgpu.internal.js.GPUTextureViewDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: TextureViewDescriptor): GPUTextureViewDescriptor =
    createJsObject<GPUTextureViewDescriptor>().apply {
        if (input.label != null) label = input.label
        if (input.format != null) format = input.format.actualName
        if (input.dimension?.stringValue != null) dimension = input.dimension.stringValue
        aspect = input.aspect.name
        baseMipLevel = input.baseMipLevel
        mipLevelCount = input.mipLevelCount
        baseArrayLayer = input.baseArrayLayer
        arrayLayerCount = input.arrayLayerCount
    }