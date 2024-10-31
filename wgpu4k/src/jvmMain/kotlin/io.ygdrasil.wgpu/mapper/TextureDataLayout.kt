
package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.TextureDataLayout
import webgpu.WGPUTextureDataLayout

internal fun MemoryAllocator.map(input: TextureDataLayout) = WGPUTextureDataLayout.allocate(this).also { output ->
    output.offset = input.offset
    output.bytesPerRow = input.bytesPerRow ?: 0u
    output.rowsPerImage = input.rowsPerImage ?: 0u
}