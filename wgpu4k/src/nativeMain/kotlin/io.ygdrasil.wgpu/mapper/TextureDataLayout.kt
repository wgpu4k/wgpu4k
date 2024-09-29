@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.TextureDataLayout
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import webgpu.native.WGPUTextureDataLayout

internal fun ArenaBase.map(input: TextureDataLayout) = alloc<WGPUTextureDataLayout>().also { output ->
    output.offset = input.offset.toULong()
    output.bytesPerRow = input.bytesPerRow?.toUInt() ?: 0u
    output.rowsPerImage = input.rowsPerImage?.toUInt() ?: 0u
}