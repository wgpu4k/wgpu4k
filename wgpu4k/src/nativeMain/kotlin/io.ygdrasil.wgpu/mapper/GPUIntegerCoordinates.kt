@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.GPUIntegerCoordinates
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import webgpu.WGPUOrigin3D

internal fun ArenaBase.map(input: GPUIntegerCoordinates) = alloc<WGPUOrigin3D>().also { output ->
    output.x = input.first.toUInt()
    output.y = input.second.toUInt()
    output.z = 0.toUInt()
}