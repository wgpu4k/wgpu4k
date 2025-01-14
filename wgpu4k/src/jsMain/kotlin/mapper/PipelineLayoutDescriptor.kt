package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.PipelineLayoutDescriptor
import io.ygdrasil.webgpu.internal.js.GPUPipelineLayoutDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: PipelineLayoutDescriptor): GPUPipelineLayoutDescriptor =
    createJsObject<GPUPipelineLayoutDescriptor>().apply {
        if (input.label != null) label = input.label
        bindGroupLayouts = input.bindGroupLayouts
            .map { it.handler }.toTypedArray()
    }
