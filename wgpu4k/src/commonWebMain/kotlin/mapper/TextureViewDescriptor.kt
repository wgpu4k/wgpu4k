package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUTextureViewDescriptor
import io.ygdrasil.webgpu.WGPUTextureViewDescriptor
import io.ygdrasil.webgpu.createJsObject

internal fun map(input: GPUTextureViewDescriptor): WGPUTextureViewDescriptor =
    createJsObject<WGPUTextureViewDescriptor>().apply {
        label = input.label
        if (input.format != null) format = input.format.value
        if (input.dimension?.value != null) dimension = input.dimension.value
        aspect = input.aspect.value
        baseMipLevel = input.baseMipLevel
        mipLevelCount = input.mipLevelCount
        baseArrayLayer = input.baseArrayLayer
        arrayLayerCount = input.arrayLayerCount
    }