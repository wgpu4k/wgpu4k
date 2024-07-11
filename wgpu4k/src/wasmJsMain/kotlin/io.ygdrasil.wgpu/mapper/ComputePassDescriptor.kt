package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ComputePassDescriptor
import io.ygdrasil.wgpu.internal.js.GPUComputePassDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject

internal fun map(input: ComputePassDescriptor): GPUComputePassDescriptor = createJsObject<GPUComputePassDescriptor>().apply {
    if (input.label != null) label = input.label.toJsString()
    // TODO: timestampWrites mapping
}
