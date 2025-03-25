package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUComputePassDescriptor
import io.ygdrasil.webgpu.WGPUComputePassDescriptor
import io.ygdrasil.webgpu.createJsObject

internal fun map(input: GPUComputePassDescriptor): WGPUComputePassDescriptor =
    createJsObject<WGPUComputePassDescriptor>().apply {
        label = input.label
        // TODO: timestampWrites mapping
    }
