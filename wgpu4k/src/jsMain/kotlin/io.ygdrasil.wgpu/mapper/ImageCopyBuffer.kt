package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyBuffer
import io.ygdrasil.wgpu.internal.js.GPUImageCopyBuffer
import io.ygdrasil.wgpu.internal.js.createJsObject

internal fun map(input: ImageCopyBuffer): GPUImageCopyBuffer = createJsObject<GPUImageCopyBuffer>().apply {
    buffer = input.buffer.handler
    offset = input.offset
    bytesPerRow = input.bytesPerRow
    rowsPerImage = input.rowsPerImage
}