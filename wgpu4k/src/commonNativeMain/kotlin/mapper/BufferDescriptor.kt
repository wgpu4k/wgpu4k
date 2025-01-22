package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.toFlagUInt
import io.ygdrasil.wgpu.WGPUBufferDescriptor

internal fun MemoryAllocator.map(input: BufferDescriptor) = WGPUBufferDescriptor.allocate(this).also { output ->
    if (input.label != null) output.label = allocateFrom(input.label)
    output.size = input.size
    output.usage = input.usage.toFlagUInt()
    output.mappedAtCreation = input.mappedAtCreation
}

