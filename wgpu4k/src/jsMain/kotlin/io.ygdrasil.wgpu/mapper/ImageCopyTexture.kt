package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.GPUIntegerCoordinate
import io.ygdrasil.wgpu.ImageCopyTexture
import io.ygdrasil.wgpu.internal.js.GPUImageCopyTexture
import io.ygdrasil.wgpu.internal.js.GPUTexture

internal fun map(input: ImageCopyTexture): GPUImageCopyTexture = object : GPUImageCopyTexture {
    override var texture: GPUTexture = input.texture.handler
    override var mipLevel: GPUIntegerCoordinate = input.mipLevel
    override var origin: dynamic = input.origin.toArray()
    override var aspect: String = input.aspect.stringValue
}
