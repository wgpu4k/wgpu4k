package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.ImageCopyBuffer
import io.ygdrasil.webgpu.internal.js.GPUImageCopyBuffer
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: ImageCopyBuffer): GPUImageCopyBuffer = createJsObject<GPUImageCopyBuffer>().apply {
    buffer = input.buffer.handler
    offset = map(input.offset)
    bytesPerRow = map(input.bytesPerRow)
    rowsPerImage = map(input.rowsPerImage)
}