package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.BufferDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUBufferDescriptor
import io.ygdrasil.wgpu.toFlagInt
import io.ygdrasil.wgpu.toInt
import java.lang.foreign.Arena

internal fun Arena.map(input: BufferDescriptor) = WGPUBufferDescriptor.allocate(this).also { output ->
    WGPUBufferDescriptor.size(output, input.size)
    WGPUBufferDescriptor.usage(output, input.usage.toFlagInt())
    WGPUBufferDescriptor.mappedAtCreation(output, input.mappedAtCreation.toInt())
}

