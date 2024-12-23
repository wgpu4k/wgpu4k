package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.ComputePipelineDescriptor
import io.ygdrasil.wgpu.GPUPipelineConstantValue
import webgpu.WGPUComputePipelineDescriptor
import webgpu.WGPUConstantEntry
import webgpu.WGPUProgrammableStageDescriptor

fun MemoryAllocator.map(input: ComputePipelineDescriptor): WGPUComputePipelineDescriptor =
    WGPUComputePipelineDescriptor.allocate(this).also { output ->
        if (input.layout != null) output.layout = input.layout.handler
        if (input.label != null) map(input.label, output.label)
        map(input.compute, output.compute)
    }

fun MemoryAllocator.map(input: ComputePipelineDescriptor.ProgrammableStage, output: WGPUProgrammableStageDescriptor) {
    output.module = input.module.handler
    if (input.entryPoint != null) map(input.entryPoint, output.entryPoint)
    if (input.constants.isNotEmpty()) {
        output.constantCount = input.constants.size.toULong()
        val entries = input.constants.entries.toList()
        output.constants = WGPUConstantEntry.allocateArray(this, input.constants.size.toUInt()) { index, entry ->
            map(entries[index.toInt()], entry)
        }
    }
}

fun MemoryAllocator.map(input: Map.Entry<String, GPUPipelineConstantValue>, output: WGPUConstantEntry) {
    map(input.key, output.key)
    output.value = input.value
}
