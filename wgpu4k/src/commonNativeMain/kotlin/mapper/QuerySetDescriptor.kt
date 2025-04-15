package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUQuerySetDescriptor
import io.ygdrasil.wgpu.WGPUQuerySetDescriptor

internal fun MemoryAllocator.map(input: GPUQuerySetDescriptor): WGPUQuerySetDescriptor = WGPUQuerySetDescriptor.allocate(this).also { output ->
    map(input.label, output.label)
    output.type = input.type.value
    output.count = input.count
}