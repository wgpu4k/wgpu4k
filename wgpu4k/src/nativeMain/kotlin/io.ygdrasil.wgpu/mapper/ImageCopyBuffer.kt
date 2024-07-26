@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyBuffer
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import webgpu.WGPUImageCopyBuffer
import webgpu.WGPUTextureDataLayout

internal fun ArenaBase.map(input: ImageCopyBuffer) = alloc<WGPUImageCopyBuffer>().also { output ->
    output.buffer = input.buffer.handler
    map(input, output.layout)
}

private fun map(input: ImageCopyBuffer, output: WGPUTextureDataLayout) {
    output.offset = input.offset.toULong()
    output.bytesPerRow = input.bytesPerRow.toUInt()
    output.rowsPerImage = input.rowsPerImage.toUInt()
}

