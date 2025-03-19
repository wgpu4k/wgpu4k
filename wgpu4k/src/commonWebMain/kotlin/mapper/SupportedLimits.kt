package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.GPUSupportedLimits
import io.ygdrasil.webgpu.Limits
import io.ygdrasil.webgpu.WGPUSupportedLimits
import io.ygdrasil.webgpu.asUInt
import io.ygdrasil.webgpu.asULong

internal fun map(input: WGPUSupportedLimits): GPUSupportedLimits = Limits(
    maxTextureDimension1D = input.maxTextureDimension1D.asUInt(),
    maxTextureDimension2D = input.maxTextureDimension2D.asUInt(),
    maxTextureDimension3D = input.maxTextureDimension3D.asUInt(),
    maxTextureArrayLayers = input.maxTextureArrayLayers.asUInt(),
    maxBindGroups = input.maxBindGroups.asUInt(),
    maxBindGroupsPlusVertexBuffers = input.maxBindGroupsPlusVertexBuffers.asUInt(),
    maxBindingsPerBindGroup = input.maxBindingsPerBindGroup.asUInt(),
    maxDynamicUniformBuffersPerPipelineLayout = input.maxDynamicUniformBuffersPerPipelineLayout.asUInt(),
    maxDynamicStorageBuffersPerPipelineLayout = input.maxDynamicStorageBuffersPerPipelineLayout.asUInt(),
    maxSampledTexturesPerShaderStage = input.maxSampledTexturesPerShaderStage.asUInt(),
    maxSamplersPerShaderStage = input.maxSamplersPerShaderStage.asUInt(),
    maxStorageBuffersPerShaderStage = input.maxStorageBuffersPerShaderStage.asUInt(),
    maxStorageTexturesPerShaderStage = input.maxStorageTexturesPerShaderStage.asUInt(),
    maxUniformBuffersPerShaderStage = input.maxUniformBuffersPerShaderStage.asUInt(),
    maxUniformBufferBindingSize = input.maxUniformBufferBindingSize.asULong(),
    maxStorageBufferBindingSize = input.maxStorageBufferBindingSize.asULong(),
    minUniformBufferOffsetAlignment = input.minUniformBufferOffsetAlignment.asUInt(),
    minStorageBufferOffsetAlignment = input.minStorageBufferOffsetAlignment.asUInt(),
    maxVertexBuffers = input.maxVertexBuffers.asUInt(),
    maxBufferSize = input.maxBufferSize.asULong(),
    maxVertexAttributes = input.maxVertexAttributes.asUInt(),
    maxVertexBufferArrayStride = input.maxVertexBufferArrayStride.asUInt(),
    maxInterStageShaderVariables = input.maxInterStageShaderVariables.asUInt(),
    maxColorAttachments = input.maxColorAttachments.asUInt(),
    maxColorAttachmentBytesPerSample = input.maxColorAttachmentBytesPerSample.asUInt(),
    maxComputeWorkgroupStorageSize = input.maxComputeWorkgroupStorageSize.asUInt(),
    maxComputeInvocationsPerWorkgroup = input.maxComputeInvocationsPerWorkgroup.asUInt(),
    maxComputeWorkgroupSizeX = input.maxComputeWorkgroupSizeX.asUInt(),
    maxComputeWorkgroupSizeY = input.maxComputeWorkgroupSizeY.asUInt(),
    maxComputeWorkgroupSizeZ = input.maxComputeWorkgroupSizeZ.asUInt(),
    maxComputeWorkgroupsPerDimension = input.maxComputeWorkgroupsPerDimension.asUInt(),
)