package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.ComputePassDescriptor
import io.ygdrasil.webgpu.internal.js.GPUComputePassDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject

internal fun map(input: ComputePassDescriptor): GPUComputePassDescriptor =
    createJsObject<GPUComputePassDescriptor>().apply {
        if (input.label != null) label = input.label.toJsString()
        // TODO: timestampWrites mapping
    }
