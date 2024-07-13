package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.PipelineLayoutDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUPipelineLayoutDescriptor
import io.ygdrasil.wgpu.internal.jvm.toPointerArray
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

internal fun Arena.map(input: PipelineLayoutDescriptor): MemorySegment = WGPUPipelineLayoutDescriptor.allocate(this).also { output ->
    if(input.label != null) WGPUPipelineLayoutDescriptor.label(output, allocateFrom(input.label))
    if (input.bindGroupLayouts.isNotEmpty()) {
        WGPUPipelineLayoutDescriptor.bindGroupLayoutCount(output, input.bindGroupLayouts.size.toLong())
        WGPUPipelineLayoutDescriptor.bindGroupLayouts(
            output,
            input.bindGroupLayouts.map { it.handler }.toPointerArray(this)
        )
    }
}

