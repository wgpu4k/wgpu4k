package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.BufferDescriptor
import io.ygdrasil.wgpu.internal.js.GPUBufferDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject
import io.ygdrasil.wgpu.toFlagInt

internal fun map(input: BufferDescriptor): GPUBufferDescriptor = createJsObject<GPUBufferDescriptor>().apply {
    size = input.size
    usage = input.usage.toFlagInt()
    mappedAtCreation = input.mappedAtCreation
    if (input.label != null) label = input.label
}
