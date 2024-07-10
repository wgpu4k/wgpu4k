package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.CommandEncoderDescriptor
import io.ygdrasil.wgpu.internal.js.GPUCommandEncoderDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject

internal fun map(input: CommandEncoderDescriptor): GPUCommandEncoderDescriptor =
    createJsObject<GPUCommandEncoderDescriptor>().apply {
        if (input.label != null) label = input.label
    }