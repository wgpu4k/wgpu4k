package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.Limits
import io.ygdrasil.wgpu.internal.js.GPUSupportedLimits

internal fun map(input: GPUSupportedLimits): Limits = Limits(
    maxTextureDimension1D = input.maxTextureDimension1D,
    maxTextureDimension2D = input.maxTextureDimension2D,
    maxTextureDimension3D = input.maxTextureDimension3D,
    maxTextureArrayLayers = input.maxTextureArrayLayers,
    maxBindGroups = input.maxBindGroups,
    maxBindGroupsPlusVertexBuffers = input.maxBindGroupsPlusVertexBuffers,
    maxBindingsPerBindGroup = input.maxBindingsPerBindGroup,
    maxDynamicUniformBuffersPerPipelineLayout = input.maxDynamicUniformBuffersPerPipelineLayout,
    maxDynamicStorageBuffersPerPipelineLayout = input.maxDynamicStorageBuffersPerPipelineLayout,
    maxSampledTexturesPerShaderStage = input.maxSampledTexturesPerShaderStage,
    maxSamplersPerShaderStage = input.maxSamplersPerShaderStage,
    maxStorageBuffersPerShaderStage = input.maxStorageBuffersPerShaderStage,
    maxStorageTexturesPerShaderStage = input.maxStorageTexturesPerShaderStage,
    maxUniformBuffersPerShaderStage = input.maxUniformBuffersPerShaderStage,
    maxUniformBufferBindingSize = input.maxUniformBufferBindingSize,
    maxStorageBufferBindingSize = input.maxStorageBufferBindingSize,
    minUniformBufferOffsetAlignment = input.minUniformBufferOffsetAlignment,
    minStorageBufferOffsetAlignment = input.minStorageBufferOffsetAlignment,
    maxVertexBuffers = input.maxVertexBuffers,
    maxBufferSize = input.maxBufferSize,
    maxVertexAttributes = input.maxVertexAttributes,
    maxVertexBufferArrayStride = input.maxVertexBufferArrayStride,
    maxInterStageShaderVariables = input.maxInterStageShaderVariables,
    maxColorAttachments = input.maxColorAttachments,
    maxColorAttachmentBytesPerSample = input.maxColorAttachmentBytesPerSample,
    maxComputeWorkgroupStorageSize = input.maxComputeWorkgroupStorageSize,
    maxComputeInvocationsPerWorkgroup = input.maxComputeInvocationsPerWorkgroup,
    maxComputeWorkgroupSizeX = input.maxComputeWorkgroupSizeX,
    maxComputeWorkgroupSizeY = input.maxComputeWorkgroupSizeY,
    maxComputeWorkgroupSizeZ = input.maxComputeWorkgroupSizeZ,
    maxComputeWorkgroupsPerDimension = input.maxComputeWorkgroupsPerDimension,
)