package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyTexture
import io.ygdrasil.wgpu.internal.js.GPUImageCopyTexture
import io.ygdrasil.wgpu.internal.js.createJsObject

internal fun map(input: ImageCopyTexture): GPUImageCopyTexture = createJsObject<GPUImageCopyTexture>().apply {
    texture = input.texture.handler
    mipLevel = input.mipLevel
    origin = input.origin.toArray()
    aspect = input.aspect.stringValue
}
