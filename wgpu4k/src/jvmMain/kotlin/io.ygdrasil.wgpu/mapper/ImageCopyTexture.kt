package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.ImageCopyTexture
import webgpu.WGPUImageCopyTexture

internal fun MemoryAllocator.map(input: ImageCopyTexture) = WGPUImageCopyTexture.allocate(this).also { output ->
    output.texture = input.texture.handler
    output.mipLevel = input.mipLevel
    output.aspect = input.aspect.uValue
    map(input.origin, output.origin)
}
