package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUTexelCopyTextureInfo
import io.ygdrasil.webgpu.Texture
import io.ygdrasil.wgpu.WGPUTexelCopyTextureInfo

internal fun MemoryAllocator.map(input: GPUTexelCopyTextureInfo) = WGPUTexelCopyTextureInfo.allocate(this).also { output ->
    output.texture = (input.texture as Texture).handler
    output.mipLevel = input.mipLevel
    output.aspect = input.aspect.value
    map(input.origin, output.origin)
}
