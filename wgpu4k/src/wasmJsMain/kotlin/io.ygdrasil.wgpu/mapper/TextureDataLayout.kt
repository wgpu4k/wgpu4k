package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.TextureDataLayout
import io.ygdrasil.webgpu.internal.js.GPUImageDataLayout
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.js.toJsBigInt

internal fun map(input: TextureDataLayout): GPUImageDataLayout = createJsObject<GPUImageDataLayout>().apply {
    offset = input.offset.toJsBigInt()
    bytesPerRow = input.bytesPerRow
    rowsPerImage = input.rowsPerImage
}