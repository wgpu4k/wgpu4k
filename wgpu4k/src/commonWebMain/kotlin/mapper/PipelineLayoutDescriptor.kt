package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.BindGroupLayout
import io.ygdrasil.webgpu.GPUPipelineLayoutDescriptor
import io.ygdrasil.webgpu.WGPUPipelineLayoutDescriptor
import io.ygdrasil.webgpu.createJsObject
import io.ygdrasil.webgpu.mapJsArray

internal fun map(input: GPUPipelineLayoutDescriptor): WGPUPipelineLayoutDescriptor =
    createJsObject<WGPUPipelineLayoutDescriptor>().apply {
        label = input.label
        bindGroupLayouts = input.bindGroupLayouts
            .mapJsArray { (it as BindGroupLayout).handler }
    }
