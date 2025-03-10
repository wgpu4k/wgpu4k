package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUColor
import io.ygdrasil.wgpu.WGPUColor

internal fun MemoryAllocator.map(input: GPUColor) = WGPUColor.allocate(this).also { output ->
    map(input, output)
}

internal fun map(input: GPUColor, output: WGPUColor) {
    output.r = input.r
    output.g = input.g
    output.b = input.b
    output.a = input.a
}