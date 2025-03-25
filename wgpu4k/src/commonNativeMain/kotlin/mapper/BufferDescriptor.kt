package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUBufferDescriptor
import io.ygdrasil.webgpu.toFlagULong
import io.ygdrasil.wgpu.WGPUBufferDescriptor

internal fun MemoryAllocator.map(input: GPUBufferDescriptor) = WGPUBufferDescriptor.allocate(this).also { output ->
    map(input.label, output.label)
    output.size = input.size
    output.usage = input.usage.toFlagULong()
    output.mappedAtCreation = input.mappedAtCreation
}

