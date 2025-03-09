package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.DeviceDescriptor
import io.ygdrasil.webgpu.GPUQueueDescriptor
import io.ygdrasil.webgpu.GPUSize64
import io.ygdrasil.webgpu.internal.js.createJsObject

// TODO: add unit test
internal fun map(input: DeviceDescriptor) = createJsObject<io.ygdrasil.webgpu.internal.js.GPUDeviceDescriptor>().apply {
    requiredFeatures = input.requiredFeatures.map { it.value }.toTypedArray()
    requiredLimits = map(input.requiredLimits)
    defaultQueue = map(input.defaultQueue)
    label = input.label
}

private fun map(input: GPUQueueDescriptor) = createJsObject<io.ygdrasil.webgpu.internal.js.GPUQueueDescriptor>().apply {
    label = input.label
}

private fun map(input: Map<String, GPUSize64>): dynamic {
    val record = js("{ }")
    input.forEach { (key, value) -> record.set(key, value) }
    return record
}