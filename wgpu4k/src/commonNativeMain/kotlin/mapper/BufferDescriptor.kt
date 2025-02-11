package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.toFlagULong
import io.ygdrasil.wgpu.WGPUBufferDescriptor

internal fun MemoryAllocator.map(input: BufferDescriptor) = WGPUBufferDescriptor.allocate(this).also { output ->
    if (input.label != null) map(input.label, output.label)
    output.size = input.size
    output.usage = input.usage.toFlagULong()
    output.mappedAtCreation = input.mappedAtCreation
}

