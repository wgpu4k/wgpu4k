package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ComputePipelineDescriptor
import io.ygdrasil.wgpu.internal.js.GPUComputePipelineDescriptor
import io.ygdrasil.wgpu.internal.js.GPUProgrammableStage
import io.ygdrasil.wgpu.internal.js.createJsObject

internal fun map(input: ComputePipelineDescriptor): GPUComputePipelineDescriptor =
    createJsObject<GPUComputePipelineDescriptor>().apply {
        compute = map(input.compute)
        layout = input.layout?.handler ?: "auto".toJsString()
        if (input.label != null) label = input.label.toJsString()
    }

private fun map(input: ComputePipelineDescriptor.ProgrammableStage): GPUProgrammableStage =
    createJsObject<GPUProgrammableStage>().apply {
        module = input.module.handler
        if (input.entryPoint != null) entryPoint = input.entryPoint.toJsString()
        //TODO map constants
    }

