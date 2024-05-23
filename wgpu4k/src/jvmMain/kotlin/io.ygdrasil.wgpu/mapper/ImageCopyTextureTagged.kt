package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyTextureTagged
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUImageCopyTexture
import java.lang.foreign.Arena

internal fun Arena.map(input: ImageCopyTextureTagged) = WGPUImageCopyTexture.allocate(this).also { output ->
    WGPUImageCopyTexture.texture(output, input.texture.handler)
    WGPUImageCopyTexture.mipLevel(output, input.mipLevel)
    WGPUImageCopyTexture.origin(output, map(input.origin))
    WGPUImageCopyTexture.aspect(output, input.aspect.value)
}

