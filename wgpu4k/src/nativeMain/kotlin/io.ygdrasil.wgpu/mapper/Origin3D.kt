@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.Origin3D
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import webgpu.native.WGPUOrigin3D

internal fun ArenaBase.map(input: Origin3D) = alloc<WGPUOrigin3D>().also { output ->
    map(input, output)
}

internal fun map(input: Origin3D, output: WGPUOrigin3D) {
    output.x = input.x.toUInt()
    output.y = input.y.toUInt()
    output.z = input.z.toUInt()
}