package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ComputePassDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUComputePassDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUComputePassTimestampWrites
import io.ygdrasil.wgpu.internal.toAddress
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(input: ComputePassDescriptor) = WGPUComputePassDescriptor.allocate(this).also { output ->
    if (input.label != null) WGPUComputePassDescriptor.label(output, allocateFrom(input.label))
    if (input.timestampWrites != null) WGPUComputePassDescriptor.timestampWrites(output, map(input.timestampWrites))
}.pointer.toAddress()

private fun SegmentAllocator.map(input: ComputePassDescriptor.ComputePassTimestampWrites) = WGPUComputePassTimestampWrites.allocate(this).also { output ->
    WGPUComputePassTimestampWrites.querySet(output, input.querySet.mhandler)
    if (input.beginningOfPassWriteIndex != null) WGPUComputePassTimestampWrites.beginningOfPassWriteIndex(output, input.beginningOfPassWriteIndex)
    if (input.endOfPassWriteIndex != null) WGPUComputePassTimestampWrites.endOfPassWriteIndex(output, input.endOfPassWriteIndex)
}