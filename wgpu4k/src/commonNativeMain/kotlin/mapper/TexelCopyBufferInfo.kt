package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.Buffer
import io.ygdrasil.webgpu.GPUTexelCopyBufferInfo
import io.ygdrasil.webgpu.GPUTexelCopyBufferLayout
import io.ygdrasil.wgpu.WGPUTexelCopyBufferInfo
import io.ygdrasil.wgpu.WGPUTexelCopyBufferLayout

internal fun MemoryAllocator.map(input: GPUTexelCopyBufferInfo) = WGPUTexelCopyBufferInfo.allocate(this).also { output ->
    output.buffer = (input.buffer as Buffer).handler
    map(input, output.layout)
}

private fun map(input: GPUTexelCopyBufferLayout, output: WGPUTexelCopyBufferLayout) {
    output.offset = input.offset
    input.bytesPerRow?.let { output.bytesPerRow = it }
    input.rowsPerImage?.let { output.rowsPerImage = it }
}

