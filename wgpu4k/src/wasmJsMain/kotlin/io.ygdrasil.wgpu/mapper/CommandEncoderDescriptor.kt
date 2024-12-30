package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.CommandEncoderDescriptor
import io.ygdrasil.webgpu.internal.js.GPUCommandEncoderDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: CommandEncoderDescriptor): GPUCommandEncoderDescriptor =
    createJsObject<GPUCommandEncoderDescriptor>().apply {
        if (input.label != null) label = input.label.toJsString()
    }