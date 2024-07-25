@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyTexture
import kotlinx.cinterop.Arena
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import webgpu.WGPUImageCopyTexture

internal fun Arena.map(input: ImageCopyTexture) = alloc<WGPUImageCopyTexture>().also { output ->
    output.texture = input.texture.handler
    output.mipLevel = input.mipLevel.toUInt()
    output.aspect = input.aspect.value.toUInt()
    map(input.origin, output.origin)
}
