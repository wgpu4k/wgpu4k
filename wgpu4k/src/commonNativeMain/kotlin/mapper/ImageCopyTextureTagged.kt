package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.ImageCopyTextureTagged
import io.ygdrasil.wgpu.WGPUImageCopyTexture

internal fun MemoryAllocator.map(input: ImageCopyTextureTagged) =
    WGPUImageCopyTexture.allocate(this).also { output ->
        output.texture = input.texture.handler
        output.mipLevel = input.mipLevel
        map(input.origin, output.origin)
        output.aspect = input.aspect.value
    }

