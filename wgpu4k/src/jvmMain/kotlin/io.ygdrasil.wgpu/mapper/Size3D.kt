package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.Size3D
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUExtent3D
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

internal fun Arena.map(input: Size3D) = WGPUExtent3D.allocate(this).also {output ->
    map(input, output)
}

internal fun Arena.map(input: Size3D, output: MemorySegment) {
    WGPUExtent3D.width(output, input.width)
    WGPUExtent3D.height(output, input.height)
    WGPUExtent3D.depthOrArrayLayers(output, input.depthOrArrayLayers)
}