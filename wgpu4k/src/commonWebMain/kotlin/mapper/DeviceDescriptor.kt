package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUDeviceDescriptor
import io.ygdrasil.webgpu.GPUQueueDescriptor
import io.ygdrasil.webgpu.WGPUDeviceDescriptor
import io.ygdrasil.webgpu.WGPUQueueDescriptor
import io.ygdrasil.webgpu.asJsString
import io.ygdrasil.webgpu.createJsObject
import io.ygdrasil.webgpu.mapJsArray

// TODO: add unit test
internal fun map(input: GPUDeviceDescriptor) = createJsObject<WGPUDeviceDescriptor>().apply {
    requiredFeatures = input.requiredFeatures.mapJsArray { it.value.asJsString() }
    input.requiredLimits?.let { requiredLimits = map(it) }
    defaultQueue = map(input.defaultQueue)
    label = input.label
}

private fun map(input: GPUQueueDescriptor) = createJsObject<WGPUQueueDescriptor>().apply {
    label = input.label
}
