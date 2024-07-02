package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.GPUSize32
import io.ygdrasil.wgpu.GPUSize64
import io.ygdrasil.wgpu.ImageCopyBuffer
import io.ygdrasil.wgpu.internal.js.GPUBuffer
import io.ygdrasil.wgpu.internal.js.GPUImageCopyBuffer

internal fun map(input: ImageCopyBuffer): GPUImageCopyBuffer = object : GPUImageCopyBuffer {
    override var buffer: GPUBuffer = input.buffer.handler
    override var offset: GPUSize64?= input.offset
    override var bytesPerRow: GPUSize32?= input.bytesPerRow
    override var rowsPerImage: GPUSize32?= input.rowsPerImage
}