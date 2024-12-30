package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.ImageCopyTexture
import io.ygdrasil.webgpu.internal.js.GPUImageCopyTexture
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: ImageCopyTexture): GPUImageCopyTexture = createJsObject<GPUImageCopyTexture>().apply {
    texture = input.texture.handler
    mipLevel = input.mipLevel
    origin = input.origin.toArray()
    aspect = input.aspect.value
}
