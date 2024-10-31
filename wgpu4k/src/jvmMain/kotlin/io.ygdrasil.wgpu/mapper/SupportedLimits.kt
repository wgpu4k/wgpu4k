package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.SupportedLimits
import webgpu.WGPUSupportedLimits

internal fun map(input: WGPUSupportedLimits): SupportedLimits = SupportedLimits(
    maxTextureDimension1D = input.limits.maxTextureDimension1D,
    maxTextureDimension2D = input.limits.maxTextureDimension2D,
    maxTextureDimension3D = input.limits.maxTextureDimension3D,
    maxTextureArrayLayers = input.limits.maxTextureArrayLayers,
    maxBindGroups = input.limits.maxBindGroups,
    maxBindGroupsPlusVertexBuffers = input.limits.maxBindGroupsPlusVertexBuffers,
    maxBindingsPerBindGroup = input.limits.maxBindingsPerBindGroup,
    maxDynamicUniformBuffersPerPipelineLayout = input.limits.maxDynamicUniformBuffersPerPipelineLayout,
    maxDynamicStorageBuffersPerPipelineLayout = input.limits.maxDynamicStorageBuffersPerPipelineLayout,
    maxSampledTexturesPerShaderStage = input.limits.maxSampledTexturesPerShaderStage,
    maxSamplersPerShaderStage = input.limits.maxSamplersPerShaderStage,
    maxStorageBuffersPerShaderStage = input.limits.maxStorageBuffersPerShaderStage,
    maxStorageTexturesPerShaderStage = input.limits.maxStorageTexturesPerShaderStage,
    maxUniformBuffersPerShaderStage = input.limits.maxUniformBuffersPerShaderStage,
    maxUniformBufferBindingSize = input.limits.maxUniformBufferBindingSize,
    maxStorageBufferBindingSize = input.limits.maxStorageBufferBindingSize,
    minUniformBufferOffsetAlignment = input.limits.minUniformBufferOffsetAlignment,
    minStorageBufferOffsetAlignment = input.limits.minStorageBufferOffsetAlignment,
    maxVertexBuffers = input.limits.maxVertexBuffers,
    maxBufferSize = input.limits.maxBufferSize,
    maxVertexAttributes = input.limits.maxVertexAttributes,
    maxVertexBufferArrayStride = input.limits.maxVertexBufferArrayStride,
    maxInterStageShaderComponents = input.limits.maxInterStageShaderComponents,
    maxInterStageShaderVariables = input.limits.maxInterStageShaderVariables,
    maxColorAttachments = input.limits.maxColorAttachments,
    maxColorAttachmentBytesPerSample = input.limits.maxColorAttachmentBytesPerSample,
    maxComputeWorkgroupStorageSize = input.limits.maxComputeWorkgroupStorageSize,
    maxComputeInvocationsPerWorkgroup = input.limits.maxComputeInvocationsPerWorkgroup,
    maxComputeWorkgroupSizeX = input.limits.maxComputeWorkgroupSizeX,
    maxComputeWorkgroupSizeY = input.limits.maxComputeWorkgroupSizeY,
    maxComputeWorkgroupSizeZ = input.limits.maxComputeWorkgroupSizeZ,
    maxComputeWorkgroupsPerDimension = input.limits.maxComputeWorkgroupsPerDimension,
)