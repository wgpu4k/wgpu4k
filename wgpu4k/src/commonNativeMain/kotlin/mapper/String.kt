package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import webgpu.WGPUStringView

internal fun MemoryAllocator.map(input: String, output: WGPUStringView) {
    output.data = allocateFrom(input)
    output.length = input.length.toULong()
}