@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyTexture
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import webgpu.native.WGPUImageCopyTexture

internal fun ArenaBase.map(input: ImageCopyTexture) = alloc<WGPUImageCopyTexture>().also { output ->
    output.texture = input.texture.handler
    output.mipLevel = input.mipLevel.toUInt()
    output.aspect = input.aspect.value.toUInt()
    map(input.origin, output.origin)
}
