package io.ygdrasil.webgpu.mapper

import ffi.MemoryAllocator
import ffi.globalMemory
import io.ygdrasil.webgpu.GPUDeviceDescriptor
import io.ygdrasil.webgpu.GPUQueueDescriptor
import io.ygdrasil.wgpu.WGPUDeviceDescriptor
import io.ygdrasil.wgpu.WGPUQueueDescriptor
import io.ygdrasil.wgpu.WGPUUncapturedErrorCallback

// TODO add unit tests
internal fun MemoryAllocator.map(input: GPUDeviceDescriptor): WGPUDeviceDescriptor =
    WGPUDeviceDescriptor.allocate(this).also { output ->
        map(input.label, output.label)
        // TODO map this
        // val requiredFeatures: Set<FeatureName> = setOf(),
        // TODO map this
        // val requiredLimits: Map<String, GPUSize64> = mapOf(),
        map(input.defaultQueue, output.defaultQueue)
        mapUncapturedError(input, output)
    }

private fun mapUncapturedError(input: GPUDeviceDescriptor, output: WGPUDeviceDescriptor) {
    input.onUncapturedError?.let { callback ->
        output.uncapturedErrorCallbackInfo.callback = WGPUUncapturedErrorCallback
            .allocate(globalMemory) { device, type, message, userdata1, userdata2 ->
                val message = message?.data?.toKString(message.length)
                errorOf(type, message)?.let { error ->
                    callback.onUncapturedError(error)
                }
            }.also { callbackHolder ->
                output.uncapturedErrorCallbackInfo.userdata2 = globalMemory
                    .bufferOfAddress(callbackHolder.handler)
                    .handler
            }
    }
}

private fun MemoryAllocator.map(input: GPUQueueDescriptor, output: WGPUQueueDescriptor) {
    map(input.label, output.label)
}
