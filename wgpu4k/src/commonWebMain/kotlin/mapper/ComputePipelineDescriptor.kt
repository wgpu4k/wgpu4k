package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUComputePipelineDescriptor
import io.ygdrasil.webgpu.GPUProgrammableStage
import io.ygdrasil.webgpu.PipelineLayout
import io.ygdrasil.webgpu.ShaderModule
import io.ygdrasil.webgpu.WGPUComputePipelineDescriptor
import io.ygdrasil.webgpu.WGPUProgrammableStage
import io.ygdrasil.webgpu.createJsObject

internal fun map(input: GPUComputePipelineDescriptor): WGPUComputePipelineDescriptor =
    createJsObject<WGPUComputePipelineDescriptor>().apply {
        label = input.label
        compute = map(input.compute)
        layout = (input.layout as PipelineLayout)?.handler ?: "auto"
    }

private fun map(input: GPUProgrammableStage): WGPUProgrammableStage =
    createJsObject<WGPUProgrammableStage>().apply {
        module = (input.module as ShaderModule).handler
        if (input.entryPoint != null) entryPoint = input.entryPoint
        //TODO map constants
    }

