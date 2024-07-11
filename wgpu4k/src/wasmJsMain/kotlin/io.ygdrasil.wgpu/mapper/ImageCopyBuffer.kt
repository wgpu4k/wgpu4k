package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyBuffer
import io.ygdrasil.wgpu.internal.js.GPUImageCopyBuffer
import io.ygdrasil.wgpu.internal.js.createJsObject
import io.ygdrasil.wgpu.internal.js.toJsNumber

internal fun map(input: ImageCopyBuffer): GPUImageCopyBuffer = createJsObject<GPUImageCopyBuffer>().apply {
    buffer = input.buffer.handler
    offset = input.offset.toJsNumber()
    bytesPerRow = input.bytesPerRow
    rowsPerImage = input.rowsPerImage
}