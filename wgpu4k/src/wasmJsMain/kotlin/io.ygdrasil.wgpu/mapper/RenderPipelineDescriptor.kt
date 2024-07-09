package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.RenderPipelineDescriptor
import io.ygdrasil.wgpu.internal.js.GPURenderPipelineDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject

internal fun map(input: RenderPipelineDescriptor): GPURenderPipelineDescriptor {
    return createJsObject<GPURenderPipelineDescriptor>().apply {

    }
}