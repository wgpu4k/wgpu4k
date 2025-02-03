package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.DeviceDescriptor
import io.ygdrasil.webgpu.QueueDescriptor
import io.ygdrasil.wgpu.WGPUDeviceDescriptor
import io.ygdrasil.wgpu.WGPUQueueDescriptor

// TODO add unit tests
internal fun MemoryAllocator.map(input: DeviceDescriptor): WGPUDeviceDescriptor =
    WGPUDeviceDescriptor.allocate(this).also { output ->
        if (input.label != null) map(input.label, output.label)
        // TODO map this
        // val requiredFeatures: Set<FeatureName> = setOf(),
        // TODO map this
        // val requiredLimits: Map<String, GPUSize64> = mapOf(),
        map(input.defaultQueue, output.defaultQueue)
    }

fun MemoryAllocator.map(input: QueueDescriptor, output: WGPUQueueDescriptor) {
    if (input.label != null) map(input.label, output.label)
}
