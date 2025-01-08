package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUIntegerCoordinates
import io.ygdrasil.wgpu.WGPUOrigin3D

internal fun MemoryAllocator.map(input: GPUIntegerCoordinates) = WGPUOrigin3D.allocate(this).also { output ->
    output.x = input.first
    output.y = input.second
    output.z = 0u
}