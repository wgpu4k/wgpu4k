package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.ImageCopyBuffer
import io.ygdrasil.wgpu.WGPUTexelCopyBufferInfo
import io.ygdrasil.wgpu.WGPUTexelCopyBufferLayout

internal fun MemoryAllocator.map(input: ImageCopyBuffer) = WGPUTexelCopyBufferInfo.allocate(this).also { output ->
    output.buffer = input.buffer.handler
    map(input, output.layout)
}

private fun map(input: ImageCopyBuffer, output: WGPUTexelCopyBufferLayout) {
    output.offset = input.offset
    output.bytesPerRow = input.bytesPerRow
    output.rowsPerImage = input.rowsPerImage
}

