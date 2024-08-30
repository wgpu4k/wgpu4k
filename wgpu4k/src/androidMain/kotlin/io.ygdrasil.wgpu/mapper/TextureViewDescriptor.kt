package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.TextureViewDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUTextureViewDescriptor
import io.ygdrasil.wgpu.internal.toAddress
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: TextureViewDescriptor) = WGPUTextureViewDescriptor.allocate(this)
    .also { output ->
        if (input.label != null) WGPUTextureViewDescriptor.label(output, allocateFrom(input.label))
        if (input.format != null) WGPUTextureViewDescriptor.format(output, input.format.value)
        if (input.dimension != null) WGPUTextureViewDescriptor.dimension(output, input.dimension.value)
        WGPUTextureViewDescriptor.aspect(output, input.aspect.value)
        WGPUTextureViewDescriptor.baseMipLevel(output, input.baseMipLevel)
        WGPUTextureViewDescriptor.mipLevelCount(output, input.mipLevelCount)
        WGPUTextureViewDescriptor.baseArrayLayer(output, input.baseArrayLayer)
        WGPUTextureViewDescriptor.arrayLayerCount(output, input.arrayLayerCount)
}.pointer.toAddress()
