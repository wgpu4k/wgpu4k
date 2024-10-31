package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.ImageCopyTextureTagged
import webgpu.WGPUImageCopyTexture

internal fun MemoryAllocator.map(input: ImageCopyTextureTagged) = WGPUImageCopyTexture.allocate(this).also { output ->
    output.texture = input.texture.handler
    output.mipLevel = input.mipLevel
    output.origin = map(input.origin)
    output.aspect = input.aspect.uValue
}

