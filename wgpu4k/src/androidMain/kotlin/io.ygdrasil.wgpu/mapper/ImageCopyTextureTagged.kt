package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyTextureTagged
import io.ygdrasil.wgpu.internal.jna.WGPUImageCopyTexture
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: ImageCopyTextureTagged) = WGPUImageCopyTexture.allocate(this).also { output ->
    WGPUImageCopyTexture.texture(output, input.texture.mhandler)
    WGPUImageCopyTexture.mipLevel(output, input.mipLevel)
    WGPUImageCopyTexture.origin(output, map(input.origin))
    WGPUImageCopyTexture.aspect(output, input.aspect.value)
}

