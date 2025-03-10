package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUTexelCopyBufferLayout
import io.ygdrasil.wgpu.WGPUTexelCopyBufferLayout

internal fun MemoryAllocator.map(input: GPUTexelCopyBufferLayout) = WGPUTexelCopyBufferLayout.allocate(this).also { output ->
    output.offset = input.offset
    input.bytesPerRow?.let { output.bytesPerRow = it }
    input.rowsPerImage?.let { output.rowsPerImage = it }
}