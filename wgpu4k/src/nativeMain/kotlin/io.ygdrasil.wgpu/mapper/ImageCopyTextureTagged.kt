@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyTextureTagged
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import webgpu.native.WGPUImageCopyTexture

internal fun ArenaBase.map(input: ImageCopyTextureTagged) = alloc<WGPUImageCopyTexture>().also { output ->
    output.texture = input.texture.handler
    output.mipLevel = input.mipLevel.toUInt()
    map(input.origin, output.origin)
    output.aspect = input.aspect.uValue
}

