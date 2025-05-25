package io.ygdrasil.webgpu.mapper

import ffi.ArrayHolder
import ffi.MemoryAllocator
import ffi.globalMemory
import io.ygdrasil.webgpu.GPUDeviceDescriptor
import io.ygdrasil.webgpu.GPUQueueDescriptor
import io.ygdrasil.webgpu.GPUSupportedLimits
import io.ygdrasil.wgpu.WGPUDeviceDescriptor
import io.ygdrasil.wgpu.WGPULimits
import io.ygdrasil.wgpu.WGPUQueueDescriptor
import io.ygdrasil.wgpu.WGPUUncapturedErrorCallback

internal fun MemoryAllocator.map(input: GPUDeviceDescriptor): WGPUDeviceDescriptor =
    WGPUDeviceDescriptor.allocate(this).also { output ->
        map(input.label, output.label)

        mapRequiredFeatures(input, output)
        input.requiredLimits?.let { output.requiredLimits = mapRequiredLimits(it) }

        map(input.defaultQueue, output.defaultQueue)
        mapUncapturedError(input, output)
    }

private fun MemoryAllocator.mapRequiredLimits(input: GPUSupportedLimits) =  WGPULimits.allocate(this).also { output ->
    output.maxTextureDimension1D = input.maxTextureDimension1D
    output.maxTextureDimension2D = input.maxTextureDimension2D
    output.maxTextureDimension3D = input.maxTextureDimension3D
    output.maxTextureArrayLayers = input.maxTextureArrayLayers
    output.maxBindGroups = input.maxBindGroups
    output.maxBindGroupsPlusVertexBuffers = input.maxBindGroupsPlusVertexBuffers
    output.maxBindingsPerBindGroup = input.maxBindingsPerBindGroup
    output.maxDynamicUniformBuffersPerPipelineLayout = input.maxDynamicUniformBuffersPerPipelineLayout
    output.maxDynamicStorageBuffersPerPipelineLayout = input.maxDynamicStorageBuffersPerPipelineLayout
    output.maxSampledTexturesPerShaderStage = input.maxSampledTexturesPerShaderStage
    output.maxSamplersPerShaderStage = input.maxSamplersPerShaderStage
    output.maxStorageBuffersPerShaderStage = input.maxStorageBuffersPerShaderStage
    output.maxStorageTexturesPerShaderStage = input.maxStorageTexturesPerShaderStage
    output.maxUniformBuffersPerShaderStage = input.maxUniformBuffersPerShaderStage
    output.maxUniformBufferBindingSize = input.maxUniformBufferBindingSize
    output.maxStorageBufferBindingSize = input.maxStorageBufferBindingSize
    output.minUniformBufferOffsetAlignment = input.minUniformBufferOffsetAlignment
    output.minStorageBufferOffsetAlignment = input.minStorageBufferOffsetAlignment
    output.maxVertexBuffers = input.maxVertexBuffers
    output.maxBufferSize = input.maxBufferSize
    output.maxVertexAttributes = input.maxVertexAttributes
    output.maxVertexBufferArrayStride = input.maxVertexBufferArrayStride
    output.maxInterStageShaderVariables = input.maxInterStageShaderVariables
    output.maxColorAttachments = input.maxColorAttachments
    output.maxColorAttachmentBytesPerSample = input.maxColorAttachmentBytesPerSample
    output.maxComputeWorkgroupStorageSize = input.maxComputeWorkgroupStorageSize
    output.maxComputeInvocationsPerWorkgroup = input.maxComputeInvocationsPerWorkgroup
    output.maxComputeWorkgroupSizeX = input.maxComputeWorkgroupSizeX
    output.maxComputeWorkgroupSizeY = input.maxComputeWorkgroupSizeY
    output.maxComputeWorkgroupSizeZ = input.maxComputeWorkgroupSizeZ
    output.maxComputeWorkgroupsPerDimension = input.maxComputeWorkgroupsPerDimension

}

private fun MemoryAllocator.mapRequiredFeatures(
    input: GPUDeviceDescriptor,
    output: WGPUDeviceDescriptor
) {
    if (input.requiredFeatures.isNotEmpty()) {
        output.requiredFeatureCount = input.requiredFeatures.size.toULong()
        output.requiredFeatures = allocateBuffer(output.requiredFeatureCount * Int.SIZE_BYTES.toULong())
            .also { it.writeUInts(input.requiredFeatures.map { it.value }.toUIntArray()) }
            .let { ArrayHolder(it.handler) }
    }
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
