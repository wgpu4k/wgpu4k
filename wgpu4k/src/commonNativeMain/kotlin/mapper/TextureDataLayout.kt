package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.TextureDataLayout
import io.ygdrasil.wgpu.WGPUTextureDataLayout

internal fun MemoryAllocator.map(input: TextureDataLayout) = WGPUTextureDataLayout.allocate(this).also { output ->
    output.offset = input.offset
    output.bytesPerRow = input.bytesPerRow ?: 0u
    output.rowsPerImage = input.rowsPerImage ?: 0u
}