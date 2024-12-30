package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.internal.js.GPUBufferDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject
import io.ygdrasil.webgpu.internal.js.toJsNumber
import io.ygdrasil.webgpu.toFlagInt

internal fun map(input: BufferDescriptor): GPUBufferDescriptor = createJsObject<GPUBufferDescriptor>().apply {
    size = input.size.toJsNumber()
    usage = input.usage.toFlagInt()
    mappedAtCreation = input.mappedAtCreation
    if (input.label != null) label = input.label.toJsString()
}
