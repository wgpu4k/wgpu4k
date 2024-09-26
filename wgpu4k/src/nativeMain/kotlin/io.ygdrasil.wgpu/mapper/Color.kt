@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.Color
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import webgpu.WGPUColor

internal fun ArenaBase.map(input: Color) = alloc<WGPUColor>().also { output ->
    map(input, output)
}

internal fun map(input: Color, output: WGPUColor) {
    output.r = input.red
    output.g = input.green
    output.b = input.blue
    output.a = input.alpha
}