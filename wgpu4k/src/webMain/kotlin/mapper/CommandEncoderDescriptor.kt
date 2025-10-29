package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUCommandEncoderDescriptor
import io.ygdrasil.webgpu.WGPUCommandEncoderDescriptor
import io.ygdrasil.webgpu.createJsObject

internal fun map(input: GPUCommandEncoderDescriptor): WGPUCommandEncoderDescriptor =
    createJsObject<WGPUCommandEncoderDescriptor>().apply {
        label = input.label
    }