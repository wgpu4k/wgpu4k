package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ComputePassDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUComputePassDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUComputePassTimestampWrites
import java.lang.foreign.Arena

internal fun Arena.map(input: ComputePassDescriptor) = WGPUComputePassDescriptor.allocate(this).also { output ->
    if (input.label != null) WGPUComputePassDescriptor.label(output, allocateFrom(input.label))
    if (input.timestampWrites != null) WGPUComputePassDescriptor.timestampWrites(output, map(input.timestampWrites))
}

private fun Arena.map(input: ComputePassDescriptor.ComputePassTimestampWrites) = WGPUComputePassTimestampWrites.allocate(this).also { output ->
    WGPUComputePassTimestampWrites.querySet(output, input.querySet.handler)
    if (input.beginningOfPassWriteIndex != null) WGPUComputePassTimestampWrites.beginningOfPassWriteIndex(output, input.beginningOfPassWriteIndex)
    if (input.endOfPassWriteIndex != null) WGPUComputePassTimestampWrites.endOfPassWriteIndex(output, input.endOfPassWriteIndex)
}