package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.RenderBundleDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPURenderBundleDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: RenderBundleDescriptor): MemorySegment = WGPURenderBundleDescriptor.allocate(this).also { output ->
    if (input.label != null) WGPURenderBundleDescriptor.label(output, allocateFrom(input.label))
}