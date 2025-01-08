package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.PipelineLayoutDescriptor
import io.ygdrasil.webgpu.internal.js.GPUPipelineLayoutDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.js.mapJsArray

internal fun map(input: PipelineLayoutDescriptor): GPUPipelineLayoutDescriptor =
    createJsObject<GPUPipelineLayoutDescriptor>().apply {
        if (input.label != null) label = input.label.toJsString()
        bindGroupLayouts = input.bindGroupLayouts
            .mapJsArray { it.handler }
    }
