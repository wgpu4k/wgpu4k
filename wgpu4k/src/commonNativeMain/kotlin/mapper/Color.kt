package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.Color
import io.ygdrasil.wgpu.WGPUColor

internal fun MemoryAllocator.map(input: Color) = WGPUColor.allocate(this).also { output ->
    map(input, output)
}

internal fun map(input: Color, output: WGPUColor) {
    output.r = input.red
    output.g = input.green
    output.b = input.blue
    output.a = input.alpha
}