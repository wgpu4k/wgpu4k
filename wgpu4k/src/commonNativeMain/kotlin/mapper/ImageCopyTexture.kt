package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.ImageCopyTexture
import webgpu.WGPUTexelCopyTextureInfo

internal fun MemoryAllocator.map(input: ImageCopyTexture) = WGPUTexelCopyTextureInfo.allocate(this).also { output ->
    output.texture = input.texture.handler
    output.mipLevel = input.mipLevel
    output.aspect = input.aspect.uValue
    map(input.origin, output.origin)
}
