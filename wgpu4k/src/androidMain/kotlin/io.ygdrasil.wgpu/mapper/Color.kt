
package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.Color
import io.ygdrasil.wgpu.internal.jna.WGPUColor
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: Color) = WGPUColor.allocate(this).also { output ->
    map(input, output)
}

internal fun SegmentAllocator.map(input: Color, output: MemorySegment) {
    WGPUColor.r(output, input.red)
    WGPUColor.g(output, input.green)
    WGPUColor.b(output, input.blue)
    WGPUColor.a(output, input.alpha)
}