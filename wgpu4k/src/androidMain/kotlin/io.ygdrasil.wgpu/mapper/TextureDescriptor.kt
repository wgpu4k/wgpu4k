package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.TextureDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUTextureDescriptor
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.toFlagInt
import java.lang.foreign.SegmentAllocator
import java.lang.foreign.ValueLayout

internal fun SegmentAllocator.map(input: TextureDescriptor) = WGPUTextureDescriptor.allocate(this).also { output ->
    if (input.label != null) WGPUTextureDescriptor.label(output, allocateFrom(input.label))
    map(input.size, WGPUTextureDescriptor.size(output))
    WGPUTextureDescriptor.format(output, input.format.value)
    WGPUTextureDescriptor.usage(output, input.usage.toFlagInt())
    WGPUTextureDescriptor.mipLevelCount(output, input.mipLevelCount)
    WGPUTextureDescriptor.sampleCount(output, input.sampleCount)
    WGPUTextureDescriptor.dimension(output, input.dimension.value)
    if (input.viewFormats.isNotEmpty()) {
        WGPUTextureDescriptor.viewFormatCount(output, input.viewFormats.size.toLong())
        val viewFormats = allocateFrom(ValueLayout.JAVA_INT, input.viewFormats.map { it.value }.toIntArray())
        WGPUTextureDescriptor.viewFormats(output, viewFormats)
    }
}.pointer.toAddress()


