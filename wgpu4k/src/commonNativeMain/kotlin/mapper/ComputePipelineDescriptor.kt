package io.ygdrasil.webgpu.mapper

import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUComputePipelineDescriptor
import io.ygdrasil.webgpu.GPUPipelineConstantValue
import io.ygdrasil.webgpu.GPUProgrammableStage
import io.ygdrasil.webgpu.PipelineLayout
import io.ygdrasil.webgpu.ShaderModule
import io.ygdrasil.wgpu.WGPUComputePipelineDescriptor
import io.ygdrasil.wgpu.WGPUComputeState
import io.ygdrasil.wgpu.WGPUConstantEntry

fun MemoryAllocator.map(input: GPUComputePipelineDescriptor): WGPUComputePipelineDescriptor =
    WGPUComputePipelineDescriptor.allocate(this).also { output ->
        if (input.layout != null) output.layout = (input.layout as PipelineLayout).handler
        map(input.label, output.label)
        map(input.compute, output.compute)
    }

fun MemoryAllocator.map(input: GPUProgrammableStage, output: WGPUComputeState) {
    output.module = (input.module as ShaderModule).handler
    input.entryPoint?.let { map(it, output.entryPoint) }
    if (input.constants.isNotEmpty()) {
        output.constantCount = input.constants.size.toULong()
        val entries = input.constants.entries.toList()
        val constants = WGPUConstantEntry.allocateArray(this, input.constants.size.toUInt()) { index, entry ->
            map(entries[index.toInt()], entry)
        }
        output.constants = WGPUConstantEntry(constants.handler)
    }
}

fun MemoryAllocator.map(input: Map.Entry<String, GPUPipelineConstantValue>, output: WGPUConstantEntry) {
    map(input.key, output.key)
    output.value = input.value
}
