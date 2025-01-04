package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.ComputePipelineDescriptor
import io.ygdrasil.webgpu.GPUPipelineConstantValue
import io.ygdrasil.wgpu.WGPUComputePipelineDescriptor
import io.ygdrasil.wgpu.WGPUConstantEntry
import io.ygdrasil.wgpu.WGPUProgrammableStageDescriptor

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
