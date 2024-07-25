@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.GPUIntegerCoordinates
import kotlinx.cinterop.Arena
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import webgpu.WGPUOrigin3D

internal fun Arena.map(input: GPUIntegerCoordinates) = alloc<WGPUOrigin3D>().also { output ->
    output.x = input.first.toUInt()
    output.y = input.second.toUInt()
    output.z = 0.toUInt()
}