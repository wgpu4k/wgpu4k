package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.WGPUStringView

internal fun MemoryAllocator.map(input: String, output: WGPUStringView) {
    output.data = allocateFrom(input)
    output.length = input.length.toULong()
}