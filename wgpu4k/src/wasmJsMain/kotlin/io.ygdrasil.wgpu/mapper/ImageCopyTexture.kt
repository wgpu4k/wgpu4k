package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyTexture
import io.ygdrasil.wgpu.internal.js.GPUImageCopyTexture
import io.ygdrasil.wgpu.internal.js.createJsObject
import io.ygdrasil.wgpu.internal.js.toJsArray

internal fun map(input: ImageCopyTexture): GPUImageCopyTexture = createJsObject<GPUImageCopyTexture>().apply  {
    texture = input.texture.handler
    mipLevel = input.mipLevel
    origin = input.origin.toArray().map { it.toJsNumber() }.toJsArray()
    aspect = input.aspect.stringValue
}
