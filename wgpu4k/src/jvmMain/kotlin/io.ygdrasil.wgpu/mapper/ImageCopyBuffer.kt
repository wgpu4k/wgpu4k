package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.ImageCopyBuffer
import webgpu.WGPUImageCopyBuffer
import webgpu.WGPUTextureDataLayout

internal fun MemoryAllocator.map(input: ImageCopyBuffer) = WGPUImageCopyBuffer.allocate(this).also { output ->
    output.buffer = input.buffer.handler
    map(input, output.layout)
}

private fun map(input: ImageCopyBuffer, output: WGPUTextureDataLayout) {
    output.offset = input.offset
    output.bytesPerRow = input.bytesPerRow
    output.rowsPerImage = input.rowsPerImage
}

