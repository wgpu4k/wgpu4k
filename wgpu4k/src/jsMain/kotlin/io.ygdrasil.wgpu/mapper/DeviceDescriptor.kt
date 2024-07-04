package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.DeviceDescriptor
import io.ygdrasil.wgpu.GPUSize64
import io.ygdrasil.wgpu.QueueDescriptor
import io.ygdrasil.wgpu.internal.js.GPUDeviceDescriptor
import io.ygdrasil.wgpu.internal.js.GPUQueueDescriptor
import js.objects.Record

// TODO: add unit test
internal fun map(input: DeviceDescriptor): GPUDeviceDescriptor = object : GPUDeviceDescriptor {
    override var requiredFeatures: Array<String?>? = input.requiredFeatures.map { it.actualName }.toTypedArray()
    override var requiredLimits: Record<String, GPUSize64>? = map(input.requiredLimits)
    override var defaultQueue: GPUQueueDescriptor? = map(input.defaultQueue)
    override var label: String? = input.label ?: undefined
}

private fun map(input: QueueDescriptor): GPUQueueDescriptor = object : GPUQueueDescriptor {
    override var label: String? = input.label ?: undefined
}

private fun map(input: Map<String, GPUSize64>): Record<String, GPUSize64> {
    val record = Record<String, GPUSize64>()
    input.forEach { (key, value) -> record.set(key, value) }
    return record
}