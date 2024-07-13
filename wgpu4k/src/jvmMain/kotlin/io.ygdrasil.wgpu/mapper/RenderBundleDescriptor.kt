package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.RenderBundleDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPURenderBundleDescriptor
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

internal fun Arena.map(input: RenderBundleDescriptor): MemorySegment = WGPURenderBundleDescriptor.allocate(this).also { output ->
    if (input.label != null) WGPURenderBundleDescriptor.label(output, allocateFrom(input.label))
}