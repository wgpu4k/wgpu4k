package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.ImageCopyBuffer
import webgpu.WGPUTexelCopyBufferInfo
import webgpu.WGPUTexelCopyBufferLayout

internal fun MemoryAllocator.map(input: ImageCopyBuffer) = WGPUTexelCopyBufferInfo.allocate(this).also { output ->
    output.buffer = input.buffer.handler
    map(input, output.layout)
}

private fun map(input: ImageCopyBuffer, output: WGPUTexelCopyBufferLayout) {
    output.offset = input.offset
    output.bytesPerRow = input.bytesPerRow
    output.rowsPerImage = input.rowsPerImage
}

