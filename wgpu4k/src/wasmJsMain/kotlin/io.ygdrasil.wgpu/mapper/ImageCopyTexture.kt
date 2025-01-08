package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.ImageCopyTexture
import io.ygdrasil.webgpu.internal.js.GPUImageCopyTexture
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.js.mapJsArray
import io.ygdrasil.webgpu.internal.js.toJsNumber

internal fun map(input: ImageCopyTexture): GPUImageCopyTexture = createJsObject<GPUImageCopyTexture>().apply {
    texture = input.texture.handler
    mipLevel = input.mipLevel
    origin = input.origin.toArray().mapJsArray { it.toJsNumber() }
    aspect = input.aspect.value
}
