package io.ygdrasil.webgpu.mapper

import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUComputePassDescriptor
import io.ygdrasil.webgpu.GPUComputePassTimestampWrites
import io.ygdrasil.webgpu.QuerySet
import io.ygdrasil.wgpu.WGPUComputePassDescriptor
import io.ygdrasil.wgpu.WGPUPassTimestampWrites

internal fun MemoryAllocator.map(input: GPUComputePassDescriptor) =
    WGPUComputePassDescriptor.allocate(this).also { output ->
        map(input.label, output.label)
        input.timestampWrites?.let { output.timestampWrites = map(it) }
    }

private fun MemoryAllocator.map(input: GPUComputePassTimestampWrites): WGPUPassTimestampWrites =
    WGPUPassTimestampWrites.allocate(this).also { output ->
        output.querySet = (input.querySet as QuerySet).handler
        input.beginningOfPassWriteIndex?.let { output.beginningOfPassWriteIndex = it }
        input.endOfPassWriteIndex?.let { output.endOfPassWriteIndex = it }
    }
