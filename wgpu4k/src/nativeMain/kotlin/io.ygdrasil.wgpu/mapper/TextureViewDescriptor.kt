@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.TextureViewDescriptor
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cstr
import webgpu.WGPUTextureViewDescriptor

internal fun ArenaBase.map(input: TextureViewDescriptor) = alloc<WGPUTextureViewDescriptor> {
    if (input.label != null) label = input.label.cstr.getPointer(this@map)
    if (input.format != null) format = input.format.value.toUInt()
    if (input.dimension != null) dimension = input.dimension.value.toUInt()
    aspect = input.aspect.value.toUInt()
    baseMipLevel = input.baseMipLevel.toUInt()
    mipLevelCount = input.mipLevelCount.toUInt()
    baseArrayLayer = input.baseArrayLayer.toUInt()
    arrayLayerCount = input.arrayLayerCount.toUInt()
}