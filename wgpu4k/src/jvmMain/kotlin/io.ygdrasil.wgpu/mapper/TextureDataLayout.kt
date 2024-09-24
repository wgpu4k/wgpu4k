
package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.TextureDataLayout
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUTextureDataLayout
import java.lang.foreign.Arena

internal fun Arena.map(input: TextureDataLayout) = WGPUTextureDataLayout.allocate(this).also { output ->
    WGPUTextureDataLayout.offset(output, input.offset)
    WGPUTextureDataLayout.bytesPerRow(output, input.bytesPerRow ?: 0)
    WGPUTextureDataLayout.rowsPerImage(output, input.rowsPerImage ?: 0)
}