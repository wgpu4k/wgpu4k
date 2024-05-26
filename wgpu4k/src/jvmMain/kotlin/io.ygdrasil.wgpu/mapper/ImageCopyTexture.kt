package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyTexture
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUImageCopyTexture
import java.lang.foreign.Arena

internal fun Arena.map(input: ImageCopyTexture) = WGPUImageCopyTexture.allocate(this).also { output ->
    WGPUImageCopyTexture.texture(output, input.texture.handler)
    WGPUImageCopyTexture.mipLevel(output, input.mipLevel)
    WGPUImageCopyTexture.aspect(output, input.aspect.value)
    map(input.origin, WGPUImageCopyTexture.origin(output))
}
