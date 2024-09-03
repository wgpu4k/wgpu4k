package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.PipelineLayoutDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUPipelineLayoutDescriptor
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.internal.toNativeArray
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: PipelineLayoutDescriptor): Long =
    WGPUPipelineLayoutDescriptor.allocate(this).also { output ->
    if(input.label != null) WGPUPipelineLayoutDescriptor.label(output, allocateFrom(input.label))
    if (input.bindGroupLayouts.isNotEmpty()) {
        WGPUPipelineLayoutDescriptor.bindGroupLayoutCount(output, input.bindGroupLayouts.size.toLong())
        WGPUPipelineLayoutDescriptor.bindGroupLayouts(
            output,
            input.bindGroupLayouts.map { it.handler }.toNativeArray(this.arena)
                .let { MemorySegment(it, 0) }
        )
    }
    }.pointer.toAddress()

