package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.PipelineLayoutDescriptor
import io.ygdrasil.wgpu.internal.js.GPUPipelineLayoutDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject
import io.ygdrasil.wgpu.internal.js.mapJsArray

internal fun map(input: PipelineLayoutDescriptor): GPUPipelineLayoutDescriptor =
    createJsObject<GPUPipelineLayoutDescriptor>().apply {
        if (input.label != null) label = input.label.toJsString()
        bindGroupLayouts = input.bindGroupLayouts
            .mapJsArray { it.handler }
    }
