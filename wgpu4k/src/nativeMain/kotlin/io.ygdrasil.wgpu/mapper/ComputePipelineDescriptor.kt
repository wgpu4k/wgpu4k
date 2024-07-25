@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ComputePipelineDescriptor
import kotlinx.cinterop.*
import webgpu.*

fun Arena.map(input: ComputePipelineDescriptor): MemorySegment =
    WGPUComputePipelineDescriptor.allocate(this).also { output ->
        if (input.layout != null) WGPUComputePipelineDescriptor.layout(output, input.layout.handler)
        if (input.label != null) WGPUComputePipelineDescriptor.label(output, allocateFrom(input.label))
        map(input.compute, WGPUComputePipelineDescriptor.compute(output))
    }

fun Arena.map(input: ComputePipelineDescriptor.ProgrammableStage, output: MemorySegment) {
    WGPUProgrammableStageDescriptor.module(output, input.module.handler)
    if (input.entryPoint != null) WGPUProgrammableStageDescriptor.entryPoint(output, allocateFrom(input.entryPoint))
    if (input.constants.isNotEmpty()) {
        WGPUProgrammableStageDescriptor.constantCount(output, input.constants.size.toLong())
        val constants = WGPUConstantEntry.allocateArray(input.constants.size.toLong(), this)
        input.constants.entries.forEachIndexed { index, entry ->
            map(
                entry,
                WGPUConstantEntry.asSlice(constants, index.toLong())
            )
        }
        WGPUProgrammableStageDescriptor.constants(output, constants)
    }
}

fun Arena.map(input: Map.Entry<String, GPUPipelineConstantValue>, output: MemorySegment) {
    WGPUConstantEntry.key(output, allocateFrom(input.key))
    WGPUConstantEntry.value(output, input.value)
}
