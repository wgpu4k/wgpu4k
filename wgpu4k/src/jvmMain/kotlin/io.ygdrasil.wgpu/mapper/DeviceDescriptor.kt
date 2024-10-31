package io.ygdrasil.wgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.wgpu.DeviceDescriptor
import io.ygdrasil.wgpu.QueueDescriptor
import webgpu.WGPUDeviceDescriptor
import webgpu.WGPUQueueDescriptor

// TODO add unit tests
internal fun MemoryAllocator.map(input: DeviceDescriptor): WGPUDeviceDescriptor = WGPUDeviceDescriptor.allocate(this).also { output ->
    if(input.label != null) map(input.label, output.label)
    // TODO map this
    // val requiredFeatures: Set<FeatureName> = setOf(),
    // TODO map this
    // val requiredLimits: Map<String, GPUSize64> = mapOf(),
    map(input.defaultQueue, output.defaultQueue)
}

fun MemoryAllocator.map(input: QueueDescriptor, output: WGPUQueueDescriptor) {
    if(input.label != null) map(input.label, output.label)
}
