@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyTextureTagged
import kotlinx.cinterop.*
import webgpu.*

internal fun Arena.map(input: ImageCopyTextureTagged) = WGPUImageCopyTexture.allocate(this).also { output ->
    WGPUImageCopyTexture.texture(output, input.texture.handler)
    WGPUImageCopyTexture.mipLevel(output, input.mipLevel)
    WGPUImageCopyTexture.origin(output, map(input.origin))
    WGPUImageCopyTexture.aspect(output, input.aspect.value)
}

