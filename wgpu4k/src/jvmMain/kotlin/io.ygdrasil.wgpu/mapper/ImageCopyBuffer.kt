package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyBuffer
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUImageCopyBuffer
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUTextureDataLayout
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

internal fun Arena.map(input: ImageCopyBuffer) = WGPUImageCopyBuffer.allocate(this).also { output ->
    WGPUImageCopyBuffer.buffer(output, input.buffer.handler)
    map(input, WGPUImageCopyBuffer.layout(output))
}

private fun map(input: ImageCopyBuffer, output: MemorySegment) {
    WGPUTextureDataLayout.offset(output, input.offset)
    WGPUTextureDataLayout.bytesPerRow(output, input.bytesPerRow)
    WGPUTextureDataLayout.rowsPerImage(output, input.rowsPerImage)
}

