package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.GPUIntegerCoordinates
import webgpu.WGPUOrigin3D

internal fun MemoryAllocator.map(input: GPUIntegerCoordinates) = WGPUOrigin3D.allocate(this).also { output ->
    output.x = input.first
    output.y = input.second
    output.z = 0u
}