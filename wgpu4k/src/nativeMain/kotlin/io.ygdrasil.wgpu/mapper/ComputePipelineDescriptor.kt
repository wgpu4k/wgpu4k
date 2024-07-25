@file:OptIn(ExperimentalForeignApi::class, ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ComputePipelineDescriptor
import io.ygdrasil.wgpu.GPUPipelineConstantValue
import kotlinx.cinterop.*
import webgpu.WGPUComputePipelineDescriptor
import webgpu.WGPUConstantEntry
import webgpu.WGPUProgrammableStageDescriptor

internal fun Arena.map(input: ComputePipelineDescriptor) = alloc<WGPUComputePipelineDescriptor>().also { output ->
    if (input.label != null) output.label = input.label.cstr.getPointer(this)
    if (input.layout != null) output.layout = input.layout.handler
    map(input.compute, output.compute)
}

private fun Arena.map(input: ComputePipelineDescriptor.ProgrammableStage, output: WGPUProgrammableStageDescriptor) {
    output.module = input.module.handler
    if (input.entryPoint != null) output.entryPoint = input.entryPoint.cstr.getPointer(this)
    if (input.constants.isNotEmpty()) {
        output.constantCount = input.constants.size.toULong()
        val constants = allocArray<WGPUConstantEntry>(input.constants.size)
        input.constants.entries.forEachIndexed { index, entry ->
            map(entry, constants[index])
        }
        output.constants = constants
    }
}

private fun Arena.map(input: Map.Entry<String, GPUPipelineConstantValue>, output: WGPUConstantEntry) {
    output.key = input.key.cstr.getPointer(this)
    output.value = input.value
}
