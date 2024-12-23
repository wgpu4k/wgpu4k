package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.BufferDescriptor
import io.ygdrasil.wgpu.toFlagULong
import webgpu.WGPUBufferDescriptor

internal fun MemoryAllocator.map(input: BufferDescriptor) = WGPUBufferDescriptor.allocate(this).also { output ->
    output.size = input.size
    output.usage = input.usage.toFlagULong()
    output.mappedAtCreation = input.mappedAtCreation
}

