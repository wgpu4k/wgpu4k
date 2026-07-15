package io.ygdrasil.webgpu.mapper

import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUBufferDescriptor
import io.ygdrasil.wgpu.WGPUBufferDescriptor
import io.ygdrasil.wgpu.toUInt

internal fun MemoryAllocator.map(input: GPUBufferDescriptor) = WGPUBufferDescriptor.allocate(this).also { output ->
    map(input.label, output.label)
    output.size = input.size
    output.usage = input.usage.value
    output.mappedAtCreation = input.mappedAtCreation.toUInt()
}
