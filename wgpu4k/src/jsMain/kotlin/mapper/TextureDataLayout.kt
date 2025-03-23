package io.ygdrasil.webgpu.mapper
/*
import io.ygdrasil.webgpu.TextureDataLayout
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.web.GPUImageDataLayout

internal fun map(input: TextureDataLayout): GPUImageDataLayout = createJsObject<GPUImageDataLayout>().apply {
    offset = map(input.offset)
    if (input.bytesPerRow != null) bytesPerRow = map(input.bytesPerRow)
    if (input.rowsPerImage != null) rowsPerImage = map(input.rowsPerImage)
}

 */