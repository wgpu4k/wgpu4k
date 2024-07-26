@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.Size3D
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import webgpu.WGPUExtent3D

internal fun ArenaBase.map(input: Size3D) = alloc<WGPUExtent3D>().also { output ->
    map(input, output)
}

internal fun map(input: Size3D, output: WGPUExtent3D) {
    output.width = input.width.toUInt()
    output.height = input.height.toUInt()
    output.depthOrArrayLayers = input.depthOrArrayLayers.toUInt()
}