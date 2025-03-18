package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUBufferDescriptor
import io.ygdrasil.webgpu.WGPUBufferDescriptor
import io.ygdrasil.webgpu.createJsObject
import io.ygdrasil.webgpu.toFlagInt

internal fun map(input: GPUBufferDescriptor): WGPUBufferDescriptor = createJsObject<WGPUBufferDescriptor>().apply {
    label = input.label
    size = input.size
    usage = input.usage.toFlagInt()
    mappedAtCreation = input.mappedAtCreation
}
