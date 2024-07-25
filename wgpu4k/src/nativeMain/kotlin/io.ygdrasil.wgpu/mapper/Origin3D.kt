@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.Origin3D
import kotlinx.cinterop.Arena
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import webgpu.WGPUOrigin3D

internal fun Arena.map(input: Origin3D) = alloc<WGPUOrigin3D>().also { output ->
    map(input, output)
}

internal fun Arena.map(input: Origin3D, output: WGPUOrigin3D) {
    output.x = input.x.toUInt()
    output.y = input.y.toUInt()
    output.z = input.z.toUInt()
}