package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.Origin3D
import webgpu.WGPUOrigin3D

internal fun MemoryAllocator.map(input: Origin3D) = WGPUOrigin3D.allocate(this).also { output ->
    map(input, output)
}

internal fun map(input: Origin3D, output: WGPUOrigin3D) {
    output.x = input.x
    output.y = input.y
    output.z = input.z
}