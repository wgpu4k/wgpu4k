package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.DeviceDescriptor
import io.ygdrasil.wgpu.GPUSize64
import io.ygdrasil.wgpu.QueueDescriptor
import io.ygdrasil.wgpu.internal.js.GPUDeviceDescriptor
import io.ygdrasil.wgpu.internal.js.GPUQueueDescriptor
import io.ygdrasil.wgpu.internal.js.createJsObject
import js.objects.Record

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

private fun map(input: Map<String, GPUSize64>): Record<String, GPUSize64> {
    val record = Record<String, GPUSize64>()
    input.forEach { (key, value) -> record.set(key, value) }
    return record
}