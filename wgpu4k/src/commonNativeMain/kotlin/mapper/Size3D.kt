package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.Size3D
import webgpu.WGPUExtent3D

internal fun MemoryAllocator.map(input: Size3D) = WGPUExtent3D.allocate(this).also { output ->
    map(input, output)
}

internal fun map(input: Size3D, output: WGPUExtent3D) {
    output.width = input.width
    output.height = input.height
    output.depthOrArrayLayers = input.depthOrArrayLayers
}