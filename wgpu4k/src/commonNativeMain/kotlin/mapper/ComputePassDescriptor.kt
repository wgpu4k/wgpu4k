package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.ComputePassDescriptor
import io.ygdrasil.wgpu.WGPUComputePassDescriptor
import io.ygdrasil.wgpu.WGPUComputePassTimestampWrites

internal fun MemoryAllocator.map(input: ComputePassDescriptor) =
    WGPUComputePassDescriptor.allocate(this).also { output ->
        if (input.label != null) map(input.label, output.label)
        if (input.timestampWrites != null) output.timestampWrites = map(input.timestampWrites)
    }

private fun MemoryAllocator.map(input: ComputePassDescriptor.ComputePassTimestampWrites) =
    WGPUComputePassTimestampWrites.allocate(this).also { output ->
        output.querySet = input.querySet.handler
        if (input.beginningOfPassWriteIndex != null) output.beginningOfPassWriteIndex = input.beginningOfPassWriteIndex
        if (input.endOfPassWriteIndex != null) output.endOfPassWriteIndex = input.endOfPassWriteIndex
    }