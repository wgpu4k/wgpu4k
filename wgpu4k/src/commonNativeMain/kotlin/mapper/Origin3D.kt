package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUOrigin3D
import io.ygdrasil.wgpu.WGPUOrigin3D

internal fun MemoryAllocator.map(input: GPUOrigin3D) = WGPUOrigin3D.allocate(this).also { output ->
    map(input, output)
}

internal fun map(input: GPUOrigin3D, output: WGPUOrigin3D) {
    output.x = input.x
    output.y = input.y
    output.z = input.z
}