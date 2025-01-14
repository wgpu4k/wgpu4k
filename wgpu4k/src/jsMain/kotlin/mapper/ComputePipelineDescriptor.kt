package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.ComputePipelineDescriptor
import io.ygdrasil.webgpu.internal.js.GPUComputePipelineDescriptor
import io.ygdrasil.webgpu.internal.js.GPUProgrammableStage
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: ComputePipelineDescriptor): GPUComputePipelineDescriptor =
    createJsObject<GPUComputePipelineDescriptor>().apply {
        compute = map(input.compute)
        layout = input.layout?.handler ?: "auto"
        if (input.label != null) label = input.label
    }

private fun map(input: ComputePipelineDescriptor.ProgrammableStage): GPUProgrammableStage =
    createJsObject<GPUProgrammableStage>().apply {
        module = input.module.handler
        if (input.entryPoint != null) entryPoint = input.entryPoint
        //TODO map constants
    }

