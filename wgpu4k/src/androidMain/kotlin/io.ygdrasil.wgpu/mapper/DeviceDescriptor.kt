package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.DeviceDescriptor
import io.ygdrasil.wgpu.QueueDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUDeviceDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUQueueDescriptor
import java.lang.foreign.MemorySegment
import java.lang.foreign.SegmentAllocator

// TODO add unit tests
internal fun SegmentAllocator.map(input: DeviceDescriptor): MemorySegment = WGPUDeviceDescriptor.allocate(this).also { output ->
    if(input.label != null) WGPUDeviceDescriptor.label(output, allocateFrom(input.label))
    // TODO map this
    // val requiredFeatures: Set<FeatureName> = setOf(),
    // TODO map this
    // val requiredLimits: Map<String, GPUSize64> = mapOf(),
    map(input.defaultQueue, WGPUDeviceDescriptor.defaultQueue(output))
}

fun SegmentAllocator.map(input: QueueDescriptor, output: MemorySegment) {
    if(input.label != null) WGPUQueueDescriptor.label(output, allocateFrom(input.label))
}
