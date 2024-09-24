package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.TextureDataLayout
import io.ygdrasil.wgpu.internal.js.GPUImageDataLayout
import io.ygdrasil.wgpu.internal.js.createJsObject

internal fun map(input: TextureDataLayout): GPUImageDataLayout = createJsObject<GPUImageDataLayout>().apply {
    offset = input.offset
    bytesPerRow = input.bytesPerRow
    rowsPerImage = input.rowsPerImage
}