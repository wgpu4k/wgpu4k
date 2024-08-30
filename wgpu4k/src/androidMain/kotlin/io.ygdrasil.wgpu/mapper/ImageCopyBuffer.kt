package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyBuffer
import io.ygdrasil.wgpu.internal.jna.WGPUImageCopyBuffer
import io.ygdrasil.wgpu.internal.jna.WGPUTextureDataLayout
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: ImageCopyBuffer) = WGPUImageCopyBuffer.allocate(this).also { output ->
    WGPUImageCopyBuffer.buffer(output, input.buffer.mhandler)
    map(input, WGPUImageCopyBuffer.layout(output))
}

private fun map(input: ImageCopyBuffer, output: MemorySegment) {
    WGPUTextureDataLayout.offset(output, input.offset)
    WGPUTextureDataLayout.bytesPerRow(output, input.bytesPerRow)
    WGPUTextureDataLayout.rowsPerImage(output, input.rowsPerImage)
}

