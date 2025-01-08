package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.ImageCopyTexture
import io.ygdrasil.wgpu.WGPUImageCopyTexture

internal fun MemoryAllocator.map(input: ImageCopyTexture) = WGPUImageCopyTexture.allocate(this).also { output ->
    output.texture = input.texture.handler
    output.mipLevel = input.mipLevel
    output.aspect = input.aspect.value
    map(input.origin, output.origin)
}
