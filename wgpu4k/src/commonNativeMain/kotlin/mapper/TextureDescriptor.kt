package io.ygdrasil.webgpu.mapper

import ffi.ArrayHolder
import ffi.MemoryAllocator
import io.ygdrasil.webgpu.TextureDescriptor
import io.ygdrasil.webgpu.toFlagULong
import webgpu.WGPUTextureDescriptor

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
        output.viewFormats = allocateBuffer(output.viewFormatCount * Int.SIZE_BYTES.toULong())
            .also { it.writeInts(input.viewFormats.map { it.value }.toIntArray()) }
            .let { ArrayHolder(it.handler) }
    }
}

