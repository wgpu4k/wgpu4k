package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUTextureViewDescriptor
import io.ygdrasil.wgpu.WGPUTextureViewDescriptor

internal fun MemoryAllocator.map(input: GPUTextureViewDescriptor) = WGPUTextureViewDescriptor.allocate(this)
    .also { output ->

        map(input.label, output.label)
        input.format?.let { output.format = it.value }
        input.dimension?.let { output.dimension = it.value }
        output.aspect = input.aspect.value
        output.baseMipLevel = input.baseMipLevel
        // @see https://developer.mozilla.org/en-US/docs/Web/API/GPUTexture/createView#miplevelcount
        output.mipLevelCount = input.mipLevelCount ?: 1u
        output.baseArrayLayer = input.baseArrayLayer
        // @see https://developer.mozilla.org/en-US/docs/Web/API/GPUTexture/createView#arraylayercount
        output.arrayLayerCount = input.arrayLayerCount ?: 1u
    }

