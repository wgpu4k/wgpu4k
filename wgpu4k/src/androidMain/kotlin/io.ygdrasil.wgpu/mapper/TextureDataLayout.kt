
package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.TextureDataLayout
import io.ygdrasil.wgpu.internal.jna.WGPUTextureDataLayout
import io.ygdrasil.wgpu.internal.toAddress
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: TextureDataLayout) = WGPUTextureDataLayout.allocate(this).also { output ->
    WGPUTextureDataLayout.offset(output, input.offset)
    WGPUTextureDataLayout.bytesPerRow(output, input.bytesPerRow ?: 0)
    WGPUTextureDataLayout.rowsPerImage(output, input.rowsPerImage ?: 0)
}.pointer.toAddress()