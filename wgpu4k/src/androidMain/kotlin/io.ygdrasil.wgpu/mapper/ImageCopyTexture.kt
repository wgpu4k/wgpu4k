package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyTexture
import io.ygdrasil.wgpu.internal.jna.WGPUImageCopyTexture
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: ImageCopyTexture) = WGPUImageCopyTexture.allocate(this).also { output ->
    WGPUImageCopyTexture.texture(output, input.texture.mhandler)
    WGPUImageCopyTexture.mipLevel(output, input.mipLevel)
    WGPUImageCopyTexture.aspect(output, input.aspect.value)
    map(input.origin, WGPUImageCopyTexture.origin(output))
}
