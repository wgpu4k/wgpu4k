package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import io.ygdrasil.webgpu.GPUDeviceDescriptor
import io.ygdrasil.webgpu.GPUQueueDescriptor
import io.ygdrasil.wgpu.WGPUDeviceDescriptor
import io.ygdrasil.wgpu.WGPUQueueDescriptor

// TODO add unit tests
internal fun MemoryAllocator.map(input: GPUDeviceDescriptor): WGPUDeviceDescriptor =
    WGPUDeviceDescriptor.allocate(this).also { output ->
        map(input.label, output.label)
        // TODO map this
        // val requiredFeatures: Set<FeatureName> = setOf(),
        // TODO map this
        // val requiredLimits: Map<String, GPUSize64> = mapOf(),
        map(input.defaultQueue, output.defaultQueue)
    }

private fun MemoryAllocator.map(input: GPUQueueDescriptor, output: WGPUQueueDescriptor) {
    map(input.label, output.label)
}
