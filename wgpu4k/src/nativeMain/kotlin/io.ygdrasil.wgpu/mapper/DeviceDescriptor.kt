@file:OptIn(ExperimentalForeignApi::class)

package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.DeviceDescriptor
import io.ygdrasil.wgpu.QueueDescriptor
import kotlinx.cinterop.ArenaBase
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cstr
import webgpu.native.WGPUDeviceDescriptor
import webgpu.native.WGPUQueueDescriptor

// TODO add unit tests
internal fun ArenaBase.map(input: DeviceDescriptor) = alloc<WGPUDeviceDescriptor>().also { output ->
    if (input.label != null) output.label = input.label.cstr.getPointer(this)
    // TODO map this
    // val requiredFeatures: Set<FeatureName> = setOf(),
    // TODO map this
    // val requiredLimits: Map<String, GPUSize64> = mapOf(),
    map(input.defaultQueue, output.defaultQueue)
}

fun ArenaBase.map(input: QueueDescriptor, output: WGPUQueueDescriptor) {
    if (input.label != null) output.label = input.label.cstr.getPointer(this)
}
