package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.ImageCopyTextureTagged
import webgpu.WGPUTexelCopyTextureInfo

internal fun MemoryAllocator.map(input: ImageCopyTextureTagged) =
    WGPUTexelCopyTextureInfo.allocate(this).also { output ->
        output.texture = input.texture.handler
        output.mipLevel = input.mipLevel
        map(input.origin, output.origin)
        output.aspect = input.aspect.uValue
    }

