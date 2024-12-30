package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.DeviceDescriptor
import io.ygdrasil.webgpu.GPUSize64
import io.ygdrasil.webgpu.QueueDescriptor
import io.ygdrasil.webgpu.internal.js.GPUDeviceDescriptor
import io.ygdrasil.webgpu.internal.js.GPUQueueDescriptor
import io.ygdrasil.webgpu.internal.js.createJsObject

// TODO: add unit test
internal fun map(input: DeviceDescriptor): GPUDeviceDescriptor = createJsObject<GPUDeviceDescriptor>().apply {
    requiredFeatures = input.requiredFeatures.map { it.actualName }.toTypedArray()
    requiredLimits = map(input.requiredLimits)
    defaultQueue = map(input.defaultQueue)
    if (input.label != null) label = input.label
}

private fun map(input: QueueDescriptor): GPUQueueDescriptor = createJsObject<GPUQueueDescriptor>().apply {
    if (input.label != null) label = input.label
}

private fun map(input: Map<String, GPUSize64>): dynamic {
    val record = js("{ }")
    input.forEach { (key, value) -> record.set(key, value) }
    return record
}