package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUExtent3D
import io.ygdrasil.wgpu.WGPUExtent3D

internal fun MemoryAllocator.map(input: GPUExtent3D) = WGPUExtent3D.allocate(this).also { output ->
    map(input, output)
}

internal fun map(input: GPUExtent3D, output: WGPUExtent3D) {
    output.width = input.width
    output.height = input.height
    output.depthOrArrayLayers = input.depthOrArrayLayers
}