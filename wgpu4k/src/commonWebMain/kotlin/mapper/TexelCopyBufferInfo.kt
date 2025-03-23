package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.Buffer
import io.ygdrasil.webgpu.GPUTexelCopyBufferInfo
import io.ygdrasil.webgpu.WGPUTexelCopyBufferInfo
import io.ygdrasil.webgpu.asJsNumber
import io.ygdrasil.webgpu.createJsObject

internal fun map(input: GPUTexelCopyBufferInfo) = createJsObject<WGPUTexelCopyBufferInfo>().apply {
    buffer = (input.buffer as Buffer).handler
    offset = input.offset.asJsNumber()
    input.bytesPerRow?.let { bytesPerRow = it.asJsNumber() }
    input.rowsPerImage?.let { rowsPerImage = it.asJsNumber() }
}