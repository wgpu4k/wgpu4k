package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.Size3D
import io.ygdrasil.wgpu.internal.jna.WGPUExtent3D
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: Size3D) = WGPUExtent3D.allocate(this).also {output ->
    map(input, output)
}

internal fun map(input: Size3D, output: MemorySegment) {
    WGPUExtent3D.width(output, input.width)
    WGPUExtent3D.height(output, input.height)
    WGPUExtent3D.depthOrArrayLayers(output, input.depthOrArrayLayers)
}