@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import kotlinx.cinterop.*
import webgpu.*

internal fun Arena.map(input: Size3D) = WGPUExtent3D.allocate(this).also { output ->
    map(input, output)
}

internal fun map(input: Size3D, output: MemorySegment) {
    WGPUExtent3D.width(output, input.width)
    WGPUExtent3D.height(output, input.height)
    WGPUExtent3D.depthOrArrayLayers(output, input.depthOrArrayLayers)
}