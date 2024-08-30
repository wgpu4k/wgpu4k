package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ComputePipelineDescriptor
import io.ygdrasil.wgpu.GPUPipelineConstantValue
import io.ygdrasil.wgpu.internal.jna.WGPUComputePipelineDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUConstantEntry
import io.ygdrasil.wgpu.internal.jna.WGPUProgrammableStageDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator

fun SegmentAllocator.map(input: ComputePipelineDescriptor): MemorySegment = WGPUComputePipelineDescriptor.allocate(this).also { output ->
    if (input.layout != null) WGPUComputePipelineDescriptor.layout(output, input.layout.mhandler)
    if (input.label != null) WGPUComputePipelineDescriptor.label(output, allocateFrom(input.label))
    map(input.compute, WGPUComputePipelineDescriptor.compute(output))
}

fun SegmentAllocator.map(input: ComputePipelineDescriptor.ProgrammableStage, output: MemorySegment) {
    WGPUProgrammableStageDescriptor.module(output, input.module.mhandler)
    if (input.entryPoint != null) WGPUProgrammableStageDescriptor.entryPoint(output, allocateFrom(input.entryPoint))
    if (input.constants.isNotEmpty()) {
        WGPUProgrammableStageDescriptor.constantCount(output, input.constants.size.toLong())
        val constants = WGPUConstantEntry.allocateArray(input.constants.size.toLong(), this)
        input.constants.entries.forEachIndexed { index, entry -> map(entry, WGPUConstantEntry.asSlice(constants, index.toLong())) }
        WGPUProgrammableStageDescriptor.constants(output, constants)
    }
}

fun SegmentAllocator.map(input: Map.Entry<String, GPUPipelineConstantValue>, output: MemorySegment) {
    WGPUConstantEntry.key(output, allocateFrom(input.key))
    WGPUConstantEntry.value(output, input.value)
}
