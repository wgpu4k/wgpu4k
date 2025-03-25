package io.ygdrasil.webgpu.mapper

import ffi.ArrayHolder
import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUTextureDescriptor
import io.ygdrasil.webgpu.toFlagULong
import io.ygdrasil.wgpu.WGPUTextureDescriptor

internal fun MemoryAllocator.map(input: GPUTextureDescriptor) = WGPUTextureDescriptor.allocate(this).also { output ->
    map(input.label, output.label)
    map(input.size, output.size)
    output.format = input.format.value
    output.usage = input.usage.toFlagULong()
    output.mipLevelCount = input.mipLevelCount
    output.sampleCount = input.sampleCount
    output.dimension = input.dimension.value
    if (input.viewFormats.isNotEmpty()) {
        output.viewFormatCount = input.viewFormats.size.toULong()
        output.viewFormats = allocateBuffer(output.viewFormatCount * Int.SIZE_BYTES.toULong())
            .also { it.writeUInts(input.viewFormats.map { it.value }.toUIntArray()) }
            .let { ArrayHolder(it.handler) }
    }
}

