package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.ImageCopyBuffer
import io.ygdrasil.wgpu.WGPUImageCopyBuffer
import io.ygdrasil.wgpu.WGPUTextureDataLayout

internal fun MemoryAllocator.map(input: ImageCopyBuffer) = WGPUImageCopyBuffer.allocate(this).also { output ->
    output.buffer = input.buffer.handler
    map(input, output.layout)
}

private fun map(input: ImageCopyBuffer, output: WGPUTextureDataLayout) {
    output.offset = input.offset
    output.bytesPerRow = input.bytesPerRow
    output.rowsPerImage = input.rowsPerImage
}

