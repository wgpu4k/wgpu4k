package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.BufferDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUBufferDescriptor
import io.ygdrasil.wgpu.internal.toAddress
import io.ygdrasil.wgpu.toFlagInt
import io.ygdrasil.wgpu.toInt
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: BufferDescriptor) = WGPUBufferDescriptor.allocate(this).also { output ->
    WGPUBufferDescriptor.size(output, input.size)
    WGPUBufferDescriptor.usage(output, input.usage.toFlagInt())
    WGPUBufferDescriptor.mappedAtCreation(output, input.mappedAtCreation.toInt())
}.pointer.toAddress()

