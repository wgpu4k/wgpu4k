package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.TextureDescriptor
import io.ygdrasil.wgpu.toFlagULong
import webgpu.WGPUTextureDescriptor
import java.lang.foreign.ValueLayout

internal fun MemoryAllocator.map(input: TextureDescriptor) = WGPUTextureDescriptor.allocate(this).also { output ->
    if (input.label != null) map(input.label, output.label)
    map(input.size, output.size)
    output.format = input.format.uValue
    output.usage = input.usage.toFlagULong()
    output.mipLevelCount = input.mipLevelCount
    output.sampleCount = input.sampleCount
    output.dimension = input.dimension.uValue
    if (input.viewFormats.isNotEmpty()) {
        output.viewFormatCount = input.viewFormats.size.toULong()
        val viewFormats = allocateFrom(ValueLayout.JAVA_INT, *(input.viewFormats.map { it.value }.toIntArray())
        output.viewFormats = viewFormats
    }
}

